package com.jacemcpherson.controller;

import com.jacemcpherson.model.SplashModel;

public class MenuController extends BaseController {

    public MenuController(Application application) {
        super(application);
        setModel(new SplashModel(application));
    }


}
