package com.jacemcpherson.view;

import com.jacemcpherson.controller.Application;
import com.jacemcpherson.graphics.Background;
import com.jacemcpherson.graphics.GameCanvas;
import com.jacemcpherson.widget.BaseWidget;

import java.awt.*;
import java.util.ArrayList;

public abstract class BaseView {

    Application mApplication;

    private Background mBackground;

    private ArrayList<BaseWidget> mWidgets = new ArrayList<>();

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
        return getCanvas().getWidth();
    }
    public int getHeight() {
        return getCanvas().getHeight();
    }

    public int getFrame() {
        return mFrameNumber;
    }

    public void paint(Graphics g) {
        mFrameNumber++;
        for (BaseWidget w : mWidgets) {
            w.draw(g);
        }
    }

    public Point getMousePosition() {
        try {
            Point mousePoint = getCanvas().getMousePosition();
            return mousePoint;
        } catch (Exception e) {
            return null;
        }
    }

    public synchronized void addWidget(BaseWidget widget) {
        for (int i = 0; i < mWidgets.size(); i++) {
            if (mWidgets.get(i).getZPosition() > widget.getZPosition()) {
                mWidgets.add(i, widget);
                break;
            }
        }
    }

    public synchronized void removeWidget(BaseWidget widget) {
        mWidgets.remove(widget);
    }

    public void drawBackground(Graphics g) {
        mBackground.draw(g, this);
    }
}
