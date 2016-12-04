package com.jacemcpherson.controller;

import com.jacemcpherson.animation.TranslateViewAnimation;
import com.jacemcpherson.model.OptionsModel;
import com.jacemcpherson.view.OptionsView;
import com.jacemcpherson.widget.MenuButton;

public class OptionsController extends BaseController {

    public OptionsController(Application application) {
        super(application);
        setModel(new OptionsModel(application, this));

        MenuButton button480 = new MenuButton(getView(), 200, 64);
        MenuButton button540 = new MenuButton(getView(), 200, 64);
        MenuButton button720 = new MenuButton(getView(), 200, 64);

        button480.setText("640x480");
        button540.setText("720x540");
        button720.setText("1280x720");

        button480.setOnClickListener(w -> {
            button480.setChecked(true);
            button540.setChecked(false);
            button720.setChecked(false);
            getWindow().resizeWindow(0);
        });


        button540.setOnClickListener(w -> {
            button480.setChecked(false);
            button540.setChecked(true);
            button720.setChecked(false);
            getWindow().resizeWindow(1);
        });


        button720.setOnClickListener(w -> {
            button480.setChecked(false);
            button540.setChecked(false);
            button720.setChecked(true);
            getWindow().resizeWindow(2);
        });

        switch (getWindow().getWindowSize()) {
            case 0:
                button480.setChecked(true);
                break;
            case 1:
                button540.setChecked(true);
                break;
            case 2:
                button720.setChecked(true);
                break;
            default:
                break;
        }

        MenuButton goBack = new MenuButton(getView(), 200, 64);
        goBack.setOnClickListener(w ->
                popController(
                        new TranslateViewAnimation.Builder()
                                .durationSeconds(1)
                                .outView(getView())
                                .inView(getPreviousController().getView())
                                .outDirection(TranslateViewAnimation.Direction.RIGHT)
                                .inDirection(TranslateViewAnimation.Direction.LEFT)
                                .build()
                )
        );

        goBack.setText("Cancel");

        getView().addSizeButton(button480);
        getView().addSizeButton(button540);
        getView().addSizeButton(button720);
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
