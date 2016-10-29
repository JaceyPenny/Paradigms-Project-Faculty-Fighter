package com.jacemcpherson.model;

import com.jacemcpherson.view.BaseView;

public abstract class BaseModel {

    protected BaseView mView;

    public <T extends BaseView> void setView(T view) {
        mView = view;
    }

    public <T extends BaseView> T getView() {
        return (T) mView;
    }

}
