package com.jacemcpherson.view;

import com.jacemcpherson.controller.Application;
import com.jacemcpherson.graphics.Background;
import com.jacemcpherson.graphics.GameCanvas;

import java.awt.*;

public abstract class BaseView {

    Application mApplication;

    private Background mBackground;

    private int mWidth;
    private int mFrameNumber;

    BaseView(Application application) {
        mApplication = application;
    }

    public GameCanvas getCanvas() {
        return mApplication.getGameWindow().getCanvas();
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

    public int getFrame() {
        return mFrameNumber;
    }

    public void paint(Graphics g) {
        mFrameNumber++;
    }

    public Point getMousePosition() {
//        PointerInfo pointerInfo = MouseInfo.getPointerInfo();
//        if (pointerInfo != null) {
//            Point mousePosition = MouseInfo.getPointerInfo().getLocation();
//            return mousePosition;
//        } else {
//            return null;
//        }
        try {
            Point mousePoint = getCanvas().getMousePosition();
            return mousePoint;
        } catch (Exception e) {
            return null;
        }
    }

    public void drawBackground(Graphics g) {
        mBackground.draw(g, this);
    }
}
