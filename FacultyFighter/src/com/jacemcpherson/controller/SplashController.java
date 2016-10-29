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

    }

    @Override
    public void mousePressed(MouseEvent e) {
        getModel().changeBackground();
    }

    @Override
    public SplashModel getModel() {
        return (SplashModel) super.getModel();
    }
}
