package com.jacemcpherson.view;

import com.jacemcpherson.controller.Application;
import com.jacemcpherson.graphics.Draw;
import com.jacemcpherson.graphics.TextDrawingOptions;
import com.jacemcpherson.model.OptionsModel;
import com.jacemcpherson.util.FontUtil;
import com.jacemcpherson.widget.MenuButton;

import java.awt.*;
import java.util.ArrayList;

public class OptionsView extends BaseView {

    ArrayList<MenuButton> mSizeButtons = new ArrayList<>();

    public OptionsView(Application application, OptionsModel model) {
        super(application, model);
    }

    public void addSizeButton(MenuButton button) {
        addWidget(button);
        mSizeButtons.add(button);
    }

    public void repositionButtons() {
        if (mSizeButtons.size() == 0) {
            return;
        }

        int xPosition;
        int yPosition = 50;

        for (MenuButton button : mSizeButtons) {
            xPosition = getWidth() / 2 - button.getWidth() / 2;
            button.setPosition(xPosition, yPosition);
            yPosition += 72;
        }

        Point position = mSizeButtons.get(3).getPosition();
        mSizeButtons.get(3).setPosition(position.x, position.y + 100);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Draw.drawText(g, "Game difficulty:", this,
                new TextDrawingOptions()
                .verticalPosition(TextDrawingOptions.VerticalTextPosition.TOP)
                .horizontalPosition(TextDrawingOptions.HorizontalTextPosition.CENTER)
                .color(Color.white)
                .font(FontUtil.gameFont(26f))
        );
    }
}
