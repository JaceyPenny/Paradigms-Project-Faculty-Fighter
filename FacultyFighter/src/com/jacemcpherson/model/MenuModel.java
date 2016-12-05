package com.jacemcpherson.model;

import com.jacemcpherson.controller.Application;
import com.jacemcpherson.controller.MenuController;
import com.jacemcpherson.view.MenuView;

public class MenuModel extends BaseModel {

    public MenuModel(Application application, MenuController controller) {
        super(application, controller);
        setView(new MenuView(application, this));
    }

    @Override
    public MenuView getView() {
        return (MenuView) super.getView();
    }
}
