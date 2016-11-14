package com.jacemcpherson.controller;

import com.jacemcpherson.animation.ViewAnimation;
import com.jacemcpherson.model.BaseModel;
import com.jacemcpherson.view.BaseView;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class BaseController {

    private Application mApplication;

    private BaseModel mModel;

    public BaseController(Application application) {
        mApplication = application;
    }

    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    public Application getApplication() {
        return mApplication;
    }

    public <T extends BaseModel> void setModel(T model) {
        mModel = model;
    }

    public <T extends BaseModel> T getModel() {
        return (T) mModel;
    }

    public <T extends BaseView> T getView() {
        return mModel.getView();
    }

    public void moveToController(BaseController controller) {
        mApplication.getGameWindow().moveToController(controller);
    }

    public void moveToController(BaseController controller, ViewAnimation viewAnimation) {
        mApplication.getGameWindow().moveToController(controller, viewAnimation);
    }
}
