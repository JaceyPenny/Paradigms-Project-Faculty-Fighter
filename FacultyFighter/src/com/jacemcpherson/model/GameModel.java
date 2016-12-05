package com.jacemcpherson.model;

import com.jacemcpherson.controller.Application;
import com.jacemcpherson.controller.BaseController;
import com.jacemcpherson.view.GameView;

public class GameModel extends BaseModel {



    public GameModel(Application application, BaseController controller) {
        super(application, controller);
        setView(new GameView(application, this));
    }

    public void initialize() {

    }

    @Override
    public GameView getView() {
        return super.getView();
    }
}
