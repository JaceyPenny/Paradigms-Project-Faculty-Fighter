package com.jacemcpherson.controller;

import com.jacemcpherson.animation.TranslateViewAnimation;
import com.jacemcpherson.model.CharacterModel;
import com.jacemcpherson.resources.R;
import com.jacemcpherson.util.ImageUtil;
import com.jacemcpherson.util.MathUtil;
import com.jacemcpherson.widget.BaseWidget;
import com.jacemcpherson.widget.ImageWidget;
import com.jacemcpherson.widget.MenuButton;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CharacterController extends BaseController {

    public CharacterController(Application application) {
        super(application);
        setModel(new CharacterModel(application, this));

        getModel().initializeView();

        BufferedImage leftImage = ImageUtil.loadImageSynchronous(R.image.arrow_left);
        Dimension arrowDimension = MathUtil.getScaled(leftImage, 100, -1);

        ImageWidget leftButton = new ImageWidget(getView(), ImageUtil.loadImageSynchronous(R.image.arrow_left));
        leftButton.setHorizontalGravity(BaseWidget.HorizontalGravity.LEFT);
        leftButton.setVerticalGravity(BaseWidget.VerticalGravity.CENTER);
        leftButton.setPadding(20, 0, 0, 0);
        leftButton.setWidth(arrowDimension.width);
        leftButton.setHeight(arrowDimension.height);
        leftButton.setClickable(true);
        leftButton.setOnClickListener(w ->
                getModel().previousFaculty()
        );

        ImageWidget rightButton = new ImageWidget(getView(), ImageUtil.loadImageSynchronous(R.image.arrow_right));
        rightButton.setHorizontalGravity(BaseWidget.HorizontalGravity.RIGHT);
        rightButton.setVerticalGravity(BaseWidget.VerticalGravity.CENTER);
        rightButton.setPadding(0, 0, 20, 0);
        rightButton.setWidth(arrowDimension.width);
        rightButton.setHeight(arrowDimension.height);
        rightButton.setClickable(true);
        rightButton.setOnClickListener(w ->
                getModel().nextFaculty()
        );

        MenuButton cancelButton = new MenuButton(getView(), 200, 64);
        cancelButton.setText("Cancel");
        cancelButton.setHorizontalGravity(BaseWidget.HorizontalGravity.LEFT);
        cancelButton.setVerticalGravity(BaseWidget.VerticalGravity.BOTTOM);
        cancelButton.setPadding(20, 0, 0, 20);
        cancelButton.setOnClickListener(w -> {
            popController(
                    new TranslateViewAnimation.Builder()
                            .durationMillis(500)
                            .outView(getView())
                            .inView(getPreviousController().getView())
                            .outDirection(TranslateViewAnimation.Direction.RIGHT)
                            .inDirection(TranslateViewAnimation.Direction.LEFT)
                            .build()
            );
        });

        MenuButton confirmButton = new MenuButton(getView(), 200, 64);
        confirmButton.setText("Confirm");
        confirmButton.setHorizontalGravity(BaseWidget.HorizontalGravity.RIGHT);
        confirmButton.setVerticalGravity(BaseWidget.VerticalGravity.BOTTOM);
        confirmButton.setPadding(0, 0, 20, 20);
        confirmButton.setOnClickListener(w -> {
            // TODO Move to GameController
            GameController controller = new GameController(getApplication());
            moveToController(controller,
                    new TranslateViewAnimation.Builder()
                            .durationMillis(500)
                            .outView(getView())
                            .inView(controller.getView())
                            .outDirection(TranslateViewAnimation.Direction.LEFT)
                            .inDirection(TranslateViewAnimation.Direction.RIGHT)
                            .build()
            );
        });

        addWidget(leftButton);
        addWidget(rightButton);
        addWidget(cancelButton);
        addWidget(confirmButton);
    }

    @Override
    public CharacterModel getModel() {
        return super.getModel();
    }
}
