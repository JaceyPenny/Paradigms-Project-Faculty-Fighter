package com.jacemcpherson.controller;

import com.jacemcpherson.model.GameModel;

public class GameController extends BaseController {

    public GameController(Application application) {
        super(application);
        setModel(new GameModel(application, this));

        getModel().initialize();
    }

    @Override
    public GameModel getModel() {
        return super.getModel();
    }
}
