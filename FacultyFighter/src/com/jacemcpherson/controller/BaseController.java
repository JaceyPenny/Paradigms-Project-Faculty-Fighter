package com.jacemcpherson.controller;

import com.jacemcpherson.animation.ViewAnimation;
import com.jacemcpherson.model.BaseModel;
import com.jacemcpherson.resources.GameState;
import com.jacemcpherson.view.BaseView;
import com.jacemcpherson.widget.BaseWidget;

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

    public GameState getGameState() {
        return mApplication.getGameState();
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
        getWindow().moveToController(controller);
    }

    public void moveToController(BaseController controller, ViewAnimation viewAnimation) {
        getWindow().moveToController(controller, viewAnimation);
    }

    public void popController() {
        getWindow().popController();
    }

    public void popController(ViewAnimation animation) {
        getWindow().popController(animation);
    }

    public GameWindow getWindow() {
        return mApplication.getGameWindow();
    }

    public BaseController getPreviousController() {
        return getWindow().getControllerBefore(this);
    }

    public void addWidget(BaseWidget widget) {
        getView().addWidget(widget);
    }

    public void update() {}

    public void onPause() {
        getView().unloadWidgets();
    }

    public void onResume() {
        getView().reloadWidgets();
    }
}
