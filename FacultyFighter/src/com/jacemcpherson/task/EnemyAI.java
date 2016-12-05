package com.jacemcpherson.task;

import com.jacemcpherson.graphics.Enemy;
import com.jacemcpherson.graphics.Player;
import com.jacemcpherson.util.Console;

import java.util.Random;

public class EnemyAI implements Runnable {

    Player mPlayer;
    Enemy mEnemy;

    boolean mPaused = false;
    boolean mStopped = false;

    float punchDifficulty;
    float blockDifficulty;

    Random mRandomGenerator = new Random();

    float mGenerated = -1;

    private static final float[][] DIFFICULTIES = {
            {0.98f, 0.5f},
            {0.93f, 0.3f},
            {0.87f, 0.2f}
    };

    Player.PlayerDirection mWalkingDirection = Player.PlayerDirection.LEFT;

    public EnemyAI(Player player, Enemy enemy, int difficulty) {
        mPlayer = player;
        mEnemy = enemy;
        float[] diffs = DIFFICULTIES[difficulty];
        punchDifficulty = diffs[0];
        blockDifficulty = diffs[1];

    }

    public void setPaused(boolean paused) {
        mPaused = paused;
    }

    public void stop() {
        mStopped = true;
    }

    @Override
    public void run() {
        try {
            int count = 0;

            while (!mStopped) {
                if (!mPaused) {
                    // enemy logic goes in here

                    if (mEnemy.isPunching()) {
                        count++;
                        if (count > 20) {
                            count = 0;
                            mEnemy.stopPunch();
                        }
                    }

                    if (canHarmPlayer()) {
                        if (mGenerated == -1) {
                            mGenerated = mRandomGenerator.nextFloat();
                        }
                        if (mGenerated > punchDifficulty)
                            mEnemy.punch();
                    }

                    if (mPlayer.isPunching() && canBeHarmed()) {
                        if (mGenerated == -1)
                            mGenerated = mRandomGenerator.nextFloat();
                        if (mGenerated > blockDifficulty) {
                            mEnemy.block();
                        }
                        continue;
                    }

                    if (mGenerated != -1) {
                        mGenerated = -1;
                    }

                    mEnemy.stopBlock();

                    switch (mWalkingDirection) {
                        case LEFT:
                            mEnemy.walkLeft();
                            break;
                        case RIGHT:
                            mEnemy.walkRight();
                            break;
                    }

                    if (mEnemy.isFarLeft()) {
                        mWalkingDirection = Player.PlayerDirection.RIGHT;
                    }

                    if (mEnemy.isFarRight()) {
                        mWalkingDirection = Player.PlayerDirection.LEFT;
                    }


                }

                Thread.sleep(20);
            }
        } catch (InterruptedException e) {
            Console.exception(e);
        }
    }

    private boolean canHarmPlayer() {
        return mEnemy.getPunchbox().intersects(mPlayer.getHitbox());
    }
    private boolean canBeHarmed() {
        return mPlayer.getPunchbox().intersects(mEnemy.getHitbox());
    }
}
