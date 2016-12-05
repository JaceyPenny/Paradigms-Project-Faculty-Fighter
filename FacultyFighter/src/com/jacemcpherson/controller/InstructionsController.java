package com.jacemcpherson.controller;

import com.jacemcpherson.animation.TranslateViewAnimation;
import com.jacemcpherson.model.InstructionsModel;
import com.jacemcpherson.widget.BaseWidget;
import com.jacemcpherson.widget.MenuButton;

public class InstructionsController extends BaseController {
    public InstructionsController(Application application) {
        super(application);
        setModel(new InstructionsModel(application, this));

        MenuButton backButton = new MenuButton(getView());
        backButton.setHorizontalGravity(BaseWidget.HorizontalGravity.CENTER);
        backButton.setVerticalGravity(BaseWidget.VerticalGravity.BOTTOM);
        backButton.setPadding(0, 0, 0, 20);
        backButton.setText("Got it");
        backButton.setOnClickListener(w -> {
            popController(new TranslateViewAnimation.Builder()
                    .durationMillis(500)
                    .inView(getPreviousController().getView())
                    .outView(getView())
                    .inDirection(TranslateViewAnimation.Direction.LEFT)
                    .outDirection(TranslateViewAnimation.Direction.RIGHT)
                    .build());
        });

        addWidget(backButton);
    }
}
