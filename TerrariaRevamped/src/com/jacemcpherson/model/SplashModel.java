package com.jacemcpherson.model;

import com.jacemcpherson.controller.Application;
import com.jacemcpherson.view.SplashView;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SplashModel extends BaseModel {

    List<Integer> mKeysPressed = new ArrayList<>();
    Point mDrawPosition = new Point(10, 10);

    public SplashModel(Application application) {
        setView(new SplashView(application));
    }

    public void addKeyPressed(int keyCode) {
        mKeysPressed.add(keyCode);
        getView().setDisplayText(mKeysPressed);
    }

    public void setDrawPosition(int x, int y) {
        getView().setDrawPosition(x, y);
    }

    public void setDrawPosition(Point position) {
        getView().setDrawPosition(position);
    }

    public void changeBackground() {
        getView().changeToRobberBackground();
    }

    @Override
    public SplashView getView() {
        return (SplashView) super.getView();
    }

    public void clearKeysPressed() {
        mKeysPressed.clear();
        getView().setDisplayText(mKeysPressed);
    }
}
