package com.jacemcpherson.view;

import com.jacemcpherson.controller.Application;
import com.jacemcpherson.model.BaseModel;
import com.jacemcpherson.widget.MenuButton;

import java.awt.*;
import java.util.ArrayList;

public class MenuView extends BaseView {

    ArrayList<MenuButton> menuButtons = new ArrayList<>();

    public MenuView(Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    public void addMenuButton(MenuButton button) {
        addWidget(button);
        menuButtons.add(button);
    }

    public void updateViews() {
        if (menuButtons.size() == 0) {
            return;
        }

        Point position = menuButtons.get(0).getPosition();
        for (int i = 1; i < menuButtons.size(); i++) {
            position.y += 72;
            menuButtons.get(i).setPosition(position.x, position.y);
        }
    }
}
