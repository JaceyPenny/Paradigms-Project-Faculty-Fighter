package com.jacemcpherson.view;

import com.jacemcpherson.controller.Application;
import com.jacemcpherson.graphics.Draw;
import com.jacemcpherson.graphics.TextDrawingOptions;

import java.awt.*;

public class MenuView extends BaseView {

    public MenuView(Application application) {
        super(application);
    }

    @Override
    public void paint(Graphics g) {
        Draw.drawText(g, "Testing", this, TextDrawingOptions.build().color(Color.white));
    }
}
