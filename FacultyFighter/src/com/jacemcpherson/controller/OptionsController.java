package com.jacemcpherson.controller;

import com.jacemcpherson.animation.TranslateViewAnimation;
import com.jacemcpherson.model.OptionsModel;
import com.jacemcpherson.view.OptionsView;
import com.jacemcpherson.widget.MenuButton;

import static com.jacemcpherson.resources.GameState.*;

public class OptionsController extends BaseController {

    public OptionsController(Application application) {
        super(application);
        setModel(new OptionsModel(application, this));

        MenuButton buttonEasy = new MenuButton(getView(), 200, 64);
        MenuButton buttonMedi = new MenuButton(getView(), 200, 64);
        MenuButton buttonHard = new MenuButton(getView(), 200, 64);

        buttonEasy.setText("Easy");
        buttonMedi.setText("Medium");
        buttonHard.setText("Hard");

        buttonEasy.setOnClickListener(w -> {
            buttonEasy.setChecked(true);
            buttonMedi.setChecked(false);
            buttonHard.setChecked(false);
            getGameState().setDifficulty(DIFFICULTY_EASY);
        });


        buttonMedi.setOnClickListener(w -> {
            buttonEasy.setChecked(false);
            buttonMedi.setChecked(true);
            buttonHard.setChecked(false);
            getGameState().setDifficulty(DIFFICULTY_MEDI);
        });


        buttonHard.setOnClickListener(w -> {
            buttonEasy.setChecked(false);
            buttonMedi.setChecked(false);
            buttonHard.setChecked(true);
            getGameState().setDifficulty(DIFFICULTY_HARD);
        });

        switch (getGameState().getDifficulty()) {
            case DIFFICULTY_EASY:
                buttonEasy.setChecked(true);
                break;
            case DIFFICULTY_MEDI:
                buttonMedi.setChecked(true);
                break;
            case DIFFICULTY_HARD:
                buttonHard.setChecked(true);
                break;
            default:
                break;
        }

        MenuButton goBack = new MenuButton(getView());
        goBack.setOnClickListener(w ->
                popController(
                        new TranslateViewAnimation.Builder()
                                .durationMillis(500)
                                .outView(getView())
                                .inView(getPreviousController().getView())
                                .outDirection(TranslateViewAnimation.Direction.RIGHT)
                                .inDirection(TranslateViewAnimation.Direction.LEFT)
                                .build()
                )
        );

        goBack.setText("Done");

        getView().addSizeButton(buttonEasy);
        getView().addSizeButton(buttonMedi);
        getView().addSizeButton(buttonHard);
        getView().addSizeButton(goBack);

        getView().repositionButtons();
    }

    @Override
    public void onResume() {
        super.onResume();
        getView().repositionButtons();
    }

    @Override
    public OptionsView getView() {
        return (OptionsView) super.getView();
    }
}
