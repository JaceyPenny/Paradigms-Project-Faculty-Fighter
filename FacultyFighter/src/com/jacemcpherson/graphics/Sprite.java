package com.jacemcpherson.graphics;

import com.jacemcpherson.util.MathUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Sprite implements Sizeable {

    private enum SpriteType {
        ANIMATED, STATIC
    }

    public static class SpriteOptions {
        boolean sizeSet = false;
        boolean positionSet = false;

        int width, height;
        int x, y;

        public SpriteOptions() {}

        public SpriteOptions setSize(int width, int height) {
            sizeSet = true;
            this.width = width;
            this.height = height;
            return this;
        }

        public SpriteOptions setSize(Dimension d) {
            return setSize(d.width, d.height);
        }

        public SpriteOptions setPosition(int x, int y) {
            positionSet = true;
            this.x = x;
            this.y = y;
            return this;
        }

        public SpriteOptions setPosition(Point p) {
            return setPosition(p.x, p.y);
        }
    }

    private int mWidth, mHeight;

    private int mXPosition, mYPosition;

    private String mName;

    private BufferedImage mImage;
    private HashMap<Integer, BufferedImage> mAnimatedImages;

    private int mCurrentAnimation;

    private SpriteType mType = SpriteType.STATIC;

    public Sprite() {

    }

    public Sprite(SpriteOptions options) {
        applyOptions(options);
    }

    public Sprite(BufferedImage image, SpriteOptions options) {
        mType = SpriteType.STATIC;
        mImage = image;
        setSize(image.getWidth(), image.getHeight());

        applyOptions(options);
    }

    public Sprite(HashMap<Integer, BufferedImage> animatedImages, SpriteOptions options) {
        mType = SpriteType.ANIMATED;
        setAnimation(animatedImages);

        applyOptions(options);
    }

    private void applyOptions(SpriteOptions options) {
        if (options == null) return;

        if (options.positionSet) {
            setPosition(options.x, options.y);
        }

        if (options.sizeSet) {
            setSize(options.width, options.height);
        }
    }

    public void setAnimation(HashMap<Integer, BufferedImage> animatedImages) {
        mAnimatedImages = animatedImages;

        BufferedImage image = (BufferedImage) mAnimatedImages.values().toArray()[0];
        mWidth = image.getWidth();
        mHeight = image.getHeight();
    }

    public HashMap<Integer, BufferedImage> getAnimation() {
        return mAnimatedImages;
    }

    public void setSize(int width, int height) {
        switch (mType) {
            case STATIC:
                if (mImage != null) {
                    Dimension scaled = MathUtil.getScaled(mImage, width, height);
                    mWidth = scaled.width;
                    mHeight = scaled.height;
                } else {
                    mWidth = (width > 0) ? width : 0;
                    mHeight = (height > 0) ? height : 0;
                }
                break;
            case ANIMATED:
                Dimension resized = MathUtil.getScaled((BufferedImage) mAnimatedImages.values().toArray()[0], width, height);
                mWidth = resized.width;
                mHeight = resized.height;
                break;
        }
    }

    public void setPosition(int x, int y) {
        mXPosition = x;
        mYPosition = y;
    }

    public void setPosition(Point position) {
        mXPosition = position.x;
        mYPosition = position.y;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setImage(BufferedImage image) {
        mType = SpriteType.STATIC;
        mImage = image;
    }

    public void paint(Graphics g) {
        switch (mType) {
            case STATIC:
                Draw.drawImage(g, mImage, mXPosition, mYPosition, mWidth, mHeight);
                break;
            case ANIMATED:
                BufferedImage current = mAnimatedImages.get(mCurrentAnimation);
                Draw.drawImage(g, current, mXPosition, mYPosition, mWidth, mHeight);
        }
    }

    public void setCurrentAnimation(int animation) {
        mCurrentAnimation = animation;
    }

    public int getCurrentAnimation() {
        return mCurrentAnimation;
    }

    @Override
    public int getWidth() {
        return mWidth;
    }

    @Override
    public int getHeight() {
        return mHeight;
    }

    public Point getPosition() {
        return new Point(mXPosition, mYPosition);
    }
}
