package com.jacemcpherson.model;

import com.jacemcpherson.controller.Application;
import com.jacemcpherson.controller.SplashController;
import com.jacemcpherson.view.SplashView;

public class SplashModel extends BaseModel {

    public SplashModel(Application application, SplashController controller) {
        super(application, controller);
        setView(new SplashView(application, this));
    }

    @Override
    public SplashView getView() {
        return (SplashView) super.getView();
    }
}
