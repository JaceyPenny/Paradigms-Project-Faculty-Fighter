package com.jacemcpherson.view;

import com.jacemcpherson.controller.Application;
import com.jacemcpherson.graphics.Background;
import com.jacemcpherson.graphics.Draw;
import com.jacemcpherson.graphics.TextDrawingOptions;
import com.jacemcpherson.model.OptionsModel;
import com.jacemcpherson.util.Console;
import com.jacemcpherson.util.FontUtil;
import com.jacemcpherson.widget.MenuButton;

import java.awt.*;
import java.util.ArrayList;

public class OptionsView extends BaseView {

    ArrayList<MenuButton> mSizeButtons = new ArrayList<>();

    public OptionsView(Application application, OptionsModel model) {
        super(application, model);
        setBackground(new Background(Color.black));
    }

    public void addSizeButton(MenuButton button) {
        addWidget(button);
        mSizeButtons.add(button);
    }

    public void repositionButtons() {
        if (mSizeButtons.size() == 0) {
            return;
        }

        Console.d("Window size: " + getApplication().getGameWindow().getWindowSize());
        Console.d("Window width: " + getCanvas().getWidth());
        int xPosition = getWidth() / 2 - mSizeButtons.get(0).getWidth() / 2;
        int yPosition = 50;

        for (MenuButton button : mSizeButtons) {
            button.setPosition(xPosition, yPosition);
            yPosition += 72;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Draw.drawText(g, "Window size:", this,
                new TextDrawingOptions()
                .verticalPosition(TextDrawingOptions.VerticalTextPosition.TOP)
                .horizontalPosition(TextDrawingOptions.HorizontalTextPosition.CENTER)
                .color(Color.white)
                .font(FontUtil.gameFont(26f))
        );
    }
}
