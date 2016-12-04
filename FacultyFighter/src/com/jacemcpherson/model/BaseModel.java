package com.jacemcpherson.model;

import com.jacemcpherson.controller.Application;
import com.jacemcpherson.controller.BaseController;
import com.jacemcpherson.view.BaseView;

public abstract class BaseModel {

    Application mApplication;
    BaseController mController;

    public BaseModel(Application application, BaseController controller) {
        mApplication = application;
        mController = controller;
    }

    protected BaseView mView;

    public <T extends BaseView> void setView(T view) {
        mView = view;
    }

    public <T extends BaseView> T getView() {
        return (T) mView;
    }

}
