package com.jacemcpherson.graphics;

import com.jacemcpherson.view.BaseView;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameCanvas extends Canvas implements Runnable {

    private boolean mDraw = true;
    private Timer mTimer;
    private BaseView mView;
    private int mFPS;

    public GameCanvas(int framesPerSecond) {
        super();
        mFPS = framesPerSecond;

        setIgnoreRepaint(true);

        mTimer = new Timer((int) (1000f / framesPerSecond), (event) -> this.draw());
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
            requestFocus();
            return;
        }
        Graphics g = strategy.getDrawGraphics();

        if (mView != null)
            mView.paint(g);

        g.dispose();
        strategy.show();
    }

    public void stopDrawing() {
        mDraw = false;
    }
}
