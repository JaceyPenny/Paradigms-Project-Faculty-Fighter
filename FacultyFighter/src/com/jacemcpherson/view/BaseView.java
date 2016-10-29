package com.jacemcpherson.view;

import com.jacemcpherson.controller.Application;
import com.jacemcpherson.graphics.Background;

import java.awt.*;

public abstract class BaseView {

    Application mApplication;

    private Background mBackground;

    private int mWidth;

    BaseView(Application application) {
        mApplication = application;
    }


    public void setBG(Background bg) {
        mBackground = bg;
    }

    public Background getBG() {
        return mBackground;
    }

    public int getWidth() {
        return mApplication.getGameWindow().getCanvas().getWidth();
    }

    public int getHeight() {
        return mApplication.getGameWindow().getCanvas().getHeight();
    }

    public abstract void paint(Graphics g);

    public void drawBackground(Graphics g) {
        mBackground.draw(g, this);
    }
}
