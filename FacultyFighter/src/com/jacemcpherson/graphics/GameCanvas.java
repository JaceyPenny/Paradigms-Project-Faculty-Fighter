package com.jacemcpherson.graphics;

import com.jacemcpherson.animation.ViewAnimation;
import com.jacemcpherson.view.BaseView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;

public class GameCanvas extends Canvas implements Runnable, ActionListener {

    private boolean mDraw = true;
    private Timer mTimer;
    private BaseView mView;
    private ViewAnimation mViewAnimation = null;
    private int mFPS;

    public GameCanvas() {
        super();
        mFPS = ViewAnimation.GLOBAL_FPS;

        setIgnoreRepaint(true);

        mTimer = new Timer((int) (1000f / ViewAnimation.GLOBAL_FPS), this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        draw();
    }

    public void setView(BaseView view) {
        mView = view;
    }

    public BaseView getView() {
        return mView;
    }

    public int getFPS() {
        return mFPS;
    }

    @Override
    public void run() {
        mTimer.start();

        while (true) {
            if (!mDraw) {
                mTimer.stop();
            }
        }
    }

    public synchronized void draw() {

        BufferStrategy strategy = getBufferStrategy();
        if (strategy == null) {
            createBufferStrategy(2);
            return;
        }

        Graphics g = strategy.getDrawGraphics();

        if (mViewAnimation != null && mViewAnimation.isCompleted()) {
            mViewAnimation = null;
        }

        paintView(g);
        g.dispose();

        strategy.show();
    }

    public void paintView(Graphics g) {
        if (isAnimating()) {
            mViewAnimation.drawAnimation(g, getSize());
        } else {
            if (mView != null) {
                mView.paint(g);
            }
        }
    }

    public void animate(ViewAnimation viewAnimation) {
        mViewAnimation = viewAnimation;
    }

    public boolean isAnimating() {
        return mViewAnimation != null && !mViewAnimation.isCompleted();
    }

    public void stopDrawing() {
        mDraw = false;
    }
}
