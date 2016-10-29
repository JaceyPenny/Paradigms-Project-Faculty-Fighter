package com.jacemcpherson.view;

import com.jacemcpherson.controller.Application;
import com.jacemcpherson.graphics.Background;

import javax.swing.*;
import java.awt.*;

public abstract class BaseView extends JPanel {

    Application mApplication;

    private Background mBackground;


    BaseView(Application application) {
        mApplication = application;
    }


    public void setBG(Background bg) {
        mBackground = bg;
    }

    public Background getBG() {
        return mBackground;
    }

    public void drawBackground(Graphics g) {
        mBackground.draw(g, this);
    }
}
