package com.jacemcpherson.animation;

import com.jacemcpherson.view.BaseView;

import java.awt.*;

public abstract class ViewAnimation {

    public static final int GLOBAL_FPS = 60;

    protected int mDurationFrames;
    protected int mCurrentFrame = 0;

    protected BaseView mInView;
    protected BaseView mOutView;

    public static class InvalidAnimationException extends RuntimeException {
        public InvalidAnimationException(String message) {
            super(message);
        }
    }

    public ViewAnimation(int duration) {
        mDurationFrames = duration;
    }

    public int getDuration() {
        return mDurationFrames;
    }

    public void setViews(BaseView outView, BaseView inView) {
        mOutView = outView;
        mInView = inView;
    }

    public void drawAnimation(Graphics g, Dimension canvasSize) {
        mCurrentFrame++;
    }

    public boolean isCompleted() {
        return mCurrentFrame >= mDurationFrames;
    }
}
