package com.jacemcpherson.controller;

import com.jacemcpherson.model.SplashModel;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class SplashController extends BaseController {


    public SplashController(Application application) {
        super(application);
        setModel(new SplashModel(application));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            getModel().clearKeysPressed();
        } else {
            getModel().addKeyPressed(e.getKeyCode());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int xPos = e.getLocationOnScreen().x - getView().getLocationOnScreen().x;
        int yPos = e.getLocationOnScreen().y - getView().getLocationOnScreen().y;
        getModel().setDrawPosition(xPos, yPos);
    }

    @Override
    public SplashModel getModel() {
        return (SplashModel) super.getModel();
    }
}
