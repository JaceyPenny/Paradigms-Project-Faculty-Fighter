package com.jacemcpherson.view;

import com.jacemcpherson.controller.Application;
import com.jacemcpherson.graphics.Draw;
import com.jacemcpherson.graphics.ShapeDrawingOptions;
import com.jacemcpherson.graphics.TextDrawingOptions;

import java.awt.*;

public class MenuView extends BaseView {

    public MenuView(Application application) {
        super(application);


    }

    @Override
    public void paint(Graphics g) {
        Draw.drawText(g, "Testing", this, new TextDrawingOptions().color(Color.white));

        Draw.drawShape(g,
                new ShapeDrawingOptions()
                .drawLocation(ShapeDrawingOptions.DrawLocation.CENTERED)
                .stroke(Color.white)
                .fill(Color.cyan)
                .height(100)
                .width(200)
                .shape(ShapeDrawingOptions.Shape.CIRCLE)
                .position(getWidth() / 2, getHeight() / 2)

        );
    }
}
