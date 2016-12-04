package com.jacemcpherson.view;

import com.jacemcpherson.controller.Application;
import com.jacemcpherson.model.BaseModel;

import java.awt.*;

public class CharacterView extends BaseView {

    public CharacterView(Application application, BaseModel model) {
        super(application, model);
        setBackground(Color.black);
    }
}
