package com.jacemcpherson.widget;

import com.jacemcpherson.view.BaseView;

import java.awt.*;

public class MenuButton extends BaseWidget {
    protected MenuButton(BaseView parent, int width, int height) {
        this(parent, width, height, 0, 0, 0);
    }

    protected MenuButton(BaseView parent, int width, int height, int xPosition, int yPosition) {
        this(parent, width, height, xPosition, yPosition, 0);
    }

    protected MenuButton(BaseView parent, int width, int height, int zPosition) {
        this(parent, width, height, 0, 0, zPosition);
    }

    protected MenuButton(BaseView parent, int width, int height, int xPosition, int yPosition, int zPosition) {
        super(parent, width, height, xPosition, yPosition, zPosition);
    }

    @Override
    public void draw(Graphics g) {

    }
}
