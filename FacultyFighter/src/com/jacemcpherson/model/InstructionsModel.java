package com.jacemcpherson.model;

import com.jacemcpherson.controller.Application;
import com.jacemcpherson.controller.BaseController;
import com.jacemcpherson.view.InstructionsView;

public class InstructionsModel extends BaseModel {

    public InstructionsModel(Application application, BaseController controller) {
        super(application, controller);
        setView(new InstructionsView(application, this));
    }
}
