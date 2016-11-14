package com.jacemcpherson.model;

import com.jacemcpherson.controller.Application;
import com.jacemcpherson.view.SplashView;

public class SplashModel extends BaseModel {

    public SplashModel(Application application) {
        super(application);
        setView(new SplashView(application));
    }

    @Override
    public SplashView getView() {
        return (SplashView) super.getView();
    }
}
