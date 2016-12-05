package com.jacemcpherson.model;

import com.jacemcpherson.controller.Application;
import com.jacemcpherson.controller.OptionsController;
import com.jacemcpherson.view.OptionsView;

public class OptionsModel extends BaseModel {
    public OptionsModel(Application application, OptionsController controller) {
        super(application, controller);
        setView(new OptionsView(application, this));
    }
}
