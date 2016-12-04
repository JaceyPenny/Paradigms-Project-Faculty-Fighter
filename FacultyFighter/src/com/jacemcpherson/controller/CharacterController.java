package com.jacemcpherson.controller;

import com.jacemcpherson.model.CharacterModel;

public class CharacterController extends BaseController {

    public CharacterController(Application application) {
        super(application);
        setModel(new CharacterModel(application, this));
    }
}
