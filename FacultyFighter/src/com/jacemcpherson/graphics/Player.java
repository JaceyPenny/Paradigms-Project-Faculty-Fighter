package com.jacemcpherson.graphics;

import com.jacemcpherson.resources.R;
import com.jacemcpherson.resources.Resources;
import com.jacemcpherson.util.ImageUtil;
import com.jacemcpherson.util.MathUtil;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import static com.jacemcpherson.graphics.Player.PlayerDirection.LEFT;
import static com.jacemcpherson.graphics.Player.PlayerDirection.RIGHT;

public class Player extends Sprite {
    public interface PunchCallback {
        void punch(Player fromPlayer);
    }

    public enum PlayerDirection {
        LEFT, RIGHT
    }

    // 60 pixels per second
    private static final int VELOCITY = 120 / MathUtil.secondsToFrames(1);

    public static final int STRIDE_TIME_FRAMES = MathUtil.millisToFrames(250);
    public static final int PUNCH_TIME_FRAMES = MathUtil.millisToFrames(500);

    public static final int ANIMATION_STILL = 0;
    public static final int ANIMATION_PUNCH = 1;
    public static final int ANIMATION_WALK1 = 2;
    public static final int ANIMATION_WALK2 = 3;
    public static final int ANIMATION_BLOCK = 4;

    private int mCurrentFrame = 0;

    private ArrayList<Integer> mKeysPressed = new ArrayList<>();

    private HashMap<Integer, BufferedImage> mAlternateAnimation;

    private int mHealth = 100;

    private PlayerDirection mDirection = RIGHT; // player starts out facing right

    private PunchCallback mPunchCallback;

    public Player(int width, int height) {
        this(width, height, R.image.faculty_patitz);
        setName("YOU");
    }

    protected Player(int width, int height, String headFile) {
        super(PlayerMaker.makePlayerWithHead(headFile, new Dimension(width, height)), null);
        setCurrentAnimation(ANIMATION_STILL);
        mAlternateAnimation = PlayerMaker.makeReversePlayer(getAnimation());
    }

    @Override
    public void setSize(int width, int height) {
        BufferedImage originalImage = ImageUtil.loadImageSynchronous(R.image.player_still);
        Dimension newSize = MathUtil.getScaled(originalImage, width, height);

        setAnimation(Resources.getPlayerAnimation(newSize));
    }

    public void setPunchCallback(PunchCallback callback) {
        mPunchCallback = callback;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        evaluateKeys();
        int animation = getCurrentAnimation();

        switch (animation) {
            case ANIMATION_PUNCH:
                mCurrentFrame += 1;
                if (mCurrentFrame == PUNCH_TIME_FRAMES) {
                    stop();
                }
                break;
            case ANIMATION_WALK1:
            case ANIMATION_WALK2:
                mCurrentFrame += 1;
            default:
                break;
        }
    }

    public void addKeyPressed(int keyCode) {
        if (!mKeysPressed.contains(keyCode)) {
            mKeysPressed.add(keyCode);
        }
    }

    public void removeKeyPressed(int keyCode) {
        mKeysPressed.remove((Integer) keyCode);
    }

    public void clearKeys() {
        mKeysPressed.clear();
    }

    public void evaluateKeys() {
        if (isPunching()) {
            return;
        }

        if (mKeysPressed.isEmpty()) {
            stop();
            return;
        }

        if (mKeysPressed.contains(KeyEvent.VK_S)) {
            mCurrentFrame = 0;
            setCurrentAnimation(ANIMATION_BLOCK);
            return;
        }

        if (mKeysPressed.contains(KeyEvent.VK_SPACE)) {
            if (!isPunching()) {
                mPunchCallback.punch(this);
            }
            setCurrentAnimation(ANIMATION_PUNCH);
            return;
        }

        int leftIndex = mKeysPressed.indexOf(KeyEvent.VK_A);
        int rightIndex = mKeysPressed.indexOf(KeyEvent.VK_D);

        if (leftIndex >= 0 && leftIndex > rightIndex) {
            shiftPlayer(LEFT);
            setWalkingAnimation();
            return;
        }

        if (rightIndex >= 0 && rightIndex > leftIndex) {
            shiftPlayer(RIGHT);
            setWalkingAnimation();
            return;
        }

        stop();
    }

    public void stop() {
        mCurrentFrame = 0;
        setCurrentAnimation(ANIMATION_STILL);
    }

    public void setWalkingAnimation() {
        if (mCurrentFrame == 0) {
            setCurrentAnimation(ANIMATION_WALK1);
        } else {
            if (mCurrentFrame == STRIDE_TIME_FRAMES) {
                mCurrentFrame = 0;
                switch (getCurrentAnimation()) {
                    case ANIMATION_WALK1:
                        setCurrentAnimation(ANIMATION_WALK2);
                        break;
                    default:
                        setCurrentAnimation(ANIMATION_WALK1);
                        break;
                }
            }
        }
    }

    public Rectangle getHitbox() {
        Point position = getPosition();
        int hitboxWidth = (int) (getWidth() * 0.43f);

        int xShift = (mDirection == RIGHT) ? 0 : (int) (getWidth() * 0.57f);

        return new Rectangle(position.x + xShift, position.y, hitboxWidth, getHeight());
    }

    public Rectangle getPunchbox() {
        Point position = getPosition();
        int punchboxWidth = (int) (getWidth() * 0.43f);

        int xShift = (mDirection == LEFT) ? 0 : (int) (getWidth() * 0.57);

        return new Rectangle(position.x + xShift, position.y, punchboxWidth, getHeight());
    }

    public void takeHit(Player player) {

        if (isBlocking()) {
            return;
        }

        Rectangle myHitbox = getHitbox();
        Rectangle theirPunchbox = player.getPunchbox();
        if (myHitbox.intersects(theirPunchbox)) {
            mHealth -= 10;
        }
    }

    public void setHealth(int health) {
        mHealth = health;
    }

    public int getHealth() {
        return mHealth;
    }

    public boolean isPunching() {
        return getCurrentAnimation() == ANIMATION_PUNCH;
    }

    public boolean isWalking() {
        return getCurrentAnimation() == ANIMATION_WALK1 || getCurrentAnimation() == ANIMATION_WALK2;
    }

    public boolean isBlocking() {
        return getCurrentAnimation() == ANIMATION_BLOCK;
    }

    public void setDirection(PlayerDirection direction) {
        if (direction != mDirection) {
            flipPlayer();
        }
    }

    protected void flipPlayer() {
        mDirection = (mDirection == LEFT) ? RIGHT : LEFT;

        HashMap<Integer, BufferedImage> temp = getAnimation();
        setAnimation(mAlternateAnimation);
        mAlternateAnimation = temp;


    }

    public boolean isFarLeft() {
        return getPosition().x <= 0;
    }

    public boolean isFarRight() {
        return getPosition().x >= 640-getWidth();
    }

    private void shiftPlayer(PlayerDirection direction) {
        if (mDirection != direction) {
            flipPlayer();
        }
        Point position = getPosition();
        int shift = (direction == LEFT) ? -1 * VELOCITY : VELOCITY;

        int newX = position.x + shift;
        if (newX < 0) {
            newX = 0;
        }

        if (newX > 640-getWidth()) {
            newX = 640-getWidth();
        }

        setPosition(newX, position.y);
    }

    public String serialize() {
        Point position = getPosition();
        return String.format(
                "%d;%d;%d;%d",
                position.x,
                position.y,
                (mDirection == LEFT) ? 0 : 1,
                mHealth
        );
    }
}
