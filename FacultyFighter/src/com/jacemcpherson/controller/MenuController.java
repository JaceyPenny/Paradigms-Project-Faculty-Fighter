package com.jacemcpherson.controller;

import com.jacemcpherson.model.MenuModel;

public class MenuController extends BaseController {

    public MenuController(Application application) {
        super(application);
        setModel(new MenuModel(application));
    }


}
