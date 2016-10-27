package com.jacemcpherson.view;

import com.jacemcpherson.controller.Application;

import javax.swing.*;

public abstract class BaseView extends JPanel {

    Application mApplication;

    public BaseView(Application application) {
        mApplication = application;
    }

}
