package com.jacemcpherson.view;

import com.jacemcpherson.controller.Application;
import com.jacemcpherson.graphics.Background;
import com.jacemcpherson.graphics.GameCanvas;
import com.jacemcpherson.graphics.Sizeable;
import com.jacemcpherson.graphics.Sprite;
import com.jacemcpherson.model.BaseModel;
import com.jacemcpherson.widget.BaseWidget;

import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public abstract class BaseView implements Sizeable {

    Application mApplication;

    private Background mBackground;

    private BaseModel mModel;

    private ArrayList<BaseWidget> mWidgets = new ArrayList<>();

    private ArrayList<Sprite> mSprites = new ArrayList<>();

    private int mFrameNumber;

    public BaseView(Application application, BaseModel model) {
        mApplication = application;
        mModel = model;
        setBackground(Color.black);
    }

    public Application getApplication() {
        return mApplication;
    }

    public GameCanvas getCanvas() {
        return mApplication.getGameWindow().getCanvas();
    }

    public void setBackground(Background bg) {
        mBackground = bg;
    }

    public void setBackground(Color color) {
        mBackground = new Background(color);
    }

    public Background getBackground() {
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

    public <T extends BaseModel> T getModel() {
        return (T)mModel;
    }

    public void paint(Graphics g) {
        mFrameNumber++;
        if (mBackground != null) {
            mBackground.draw(g, this);
        }
        for (BaseWidget w : mWidgets) {
            w.paint(g);
        }
        for (Sprite s : mSprites) {
            s.paint(g);
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
        addListener(widget);

        boolean added = false;

        for (int i = 0; i < mWidgets.size(); i++) {
            if (mWidgets.get(i).getZPosition() > widget.getZPosition()) {
                mWidgets.add(i, widget);
                added = true;
                break;
            }
        }

        if (!added) {
            mWidgets.add(widget);
        }
    }

    public synchronized void addSprite(Sprite sprite) {
        mSprites.add(sprite);
    }

    public synchronized void removeSprite(Sprite sprite) {
        mSprites.remove(sprite);
    }

    public synchronized void unloadWidgets() {
        for (BaseWidget widget : mWidgets) {
            removeListener(widget);
        }
    }

    public synchronized void reloadWidgets() {
        for (BaseWidget widget : mWidgets) {
            addListener(widget);
        }
    }

    public synchronized void removeWidget(BaseWidget widget) {
        mWidgets.remove(widget);
        addListener(widget);
    }

    public void addListener(BaseWidget widget) {
        MouseListener[] listeners = getCanvas().getMouseListeners();

        for (MouseListener listener : listeners) {
            if (listener == widget) {
                return;
            }
        }

        getCanvas().addMouseListener(widget);
        getCanvas().addMouseMotionListener(widget);
    }

    public void removeListener(BaseWidget widget) {
        getCanvas().removeMouseListener(widget);
        getCanvas().removeMouseMotionListener(widget);
    }

    public ArrayList<BaseWidget> getWidgets() {
        return mWidgets;
    }

    public void drawBackground(Graphics g) {
        mBackground.draw(g, this);
    }
}
