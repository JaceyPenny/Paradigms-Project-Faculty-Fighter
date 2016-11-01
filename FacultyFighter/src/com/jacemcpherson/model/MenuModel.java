package com.jacemcpherson.model;

import com.jacemcpherson.controller.Application;
import com.jacemcpherson.view.MenuView;

public class MenuModel extends BaseModel {

    public MenuModel(Application application) {
        super(application);
        setView(new MenuView(application));
    }
}
