package com.jacemcpherson.model;

import com.jacemcpherson.controller.Application;
import com.jacemcpherson.view.BaseView;

public abstract class BaseModel {

    public BaseModel(Application application) {

    }

    protected BaseView mView;

    public <T extends BaseView> void setView(T view) {
        mView = view;
    }

    public <T extends BaseView> T getView() {
        return (T) mView;
    }

}
