package com.jacemcpherson.model;

import com.jacemcpherson.controller.Application;
import com.jacemcpherson.controller.BaseController;
import com.jacemcpherson.view.CharacterView;

public class CharacterModel extends BaseModel {

    public CharacterModel(Application application, BaseController controller) {
        super(application, controller);
        setView(new CharacterView(application, this));
    }
}
