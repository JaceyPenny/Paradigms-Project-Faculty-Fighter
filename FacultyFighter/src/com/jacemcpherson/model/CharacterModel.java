package com.jacemcpherson.model;

import com.jacemcpherson.controller.Application;
import com.jacemcpherson.controller.BaseController;
import com.jacemcpherson.resources.Pair;
import com.jacemcpherson.resources.Resources;
import com.jacemcpherson.view.CharacterView;

import java.util.ArrayList;

public class CharacterModel extends BaseModel {

    ArrayList<Pair<String, String>> mFaculty = Resources.getFacultyFiles();

    private int mCurrentFaculty = 0;

    public CharacterModel(Application application, BaseController controller) {
        super(application, controller);
        setView(new CharacterView(application, this));
    }

    public void initializeView() {
        updateView();
    }

    public void nextFaculty() {
        mCurrentFaculty = (mCurrentFaculty + 1) % mFaculty.size();
        updateView();
    }

    public void previousFaculty() {
        mCurrentFaculty -= 1;
        if (mCurrentFaculty < 0) {
            mCurrentFaculty += mFaculty.size();
        }
        updateView();
    }

    public void setFaculty(int selection) {
        mCurrentFaculty = selection;
        updateView();
    }

    private void updateView() {
        getView().setCurrentTeacher(mFaculty, mCurrentFaculty);
        getApplication().getGameState().setEnemySelection(mCurrentFaculty);
    }

    public Pair<String, String> getCurrentFaculty() {
        return mFaculty.get(mCurrentFaculty);
    }

    @Override
    public CharacterView getView() {
        return super.getView();
    }
}
