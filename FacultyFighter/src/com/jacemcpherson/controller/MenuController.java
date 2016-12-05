package com.jacemcpherson.controller;

import com.jacemcpherson.animation.FadeViewAnimation;
import com.jacemcpherson.animation.TranslateViewAnimation;
import com.jacemcpherson.model.MenuModel;
import com.jacemcpherson.resources.R;
import com.jacemcpherson.util.Console;
import com.jacemcpherson.util.ImageUtil;
import com.jacemcpherson.view.MenuView;
import com.jacemcpherson.widget.BaseWidget;
import com.jacemcpherson.widget.ImageWidget;
import com.jacemcpherson.widget.MenuButton;

import java.awt.image.BufferedImage;

public class MenuController extends BaseController {

    public MenuController(Application application) {
        super(application);
        setModel(new MenuModel(application, this));

        BufferedImage image = ImageUtil.loadImageSynchronous(
                R.image.bg_splash,
                -1,
                getView().getHeight() / 3
        );

        ImageWidget bgImage = new ImageWidget(getView(), image);
        bgImage.setZPosition(-1);
        bgImage.setHorizontalGravity(BaseWidget.HorizontalGravity.CENTER);
        bgImage.setVerticalGravity(BaseWidget.VerticalGravity.TOP);
        bgImage.setPadding(0, 10, 0, 0);

        addWidget(bgImage);

        MenuButton startButton = new MenuButton(getView());
        startButton.setText("Start Game");
        startButton.setOnClickListener(widget -> {
            CharacterController controller = new CharacterController(getApplication());
            moveToController(
                    controller,
                    new TranslateViewAnimation.Builder()
                            .durationMillis(500)
                            .inDirection(TranslateViewAnimation.Direction.RIGHT)
                            .outDirection(TranslateViewAnimation.Direction.LEFT)
                            .inView(controller.getView())
                            .outView(getView())
                            .build()
            );
        });
        startButton.setHorizontalGravity(BaseWidget.HorizontalGravity.CENTER);
        startButton.setVerticalGravity(BaseWidget.VerticalGravity.CENTER);

        getView().addMenuButton(startButton);

        MenuButton optionsButton = new MenuButton(getView());
        optionsButton.setText("Options");
        optionsButton.setOnClickListener(widget -> {
            Console.d("Clicked options");
            OptionsController controller = new OptionsController(getApplication());
            moveToController(
                    controller,
                    new TranslateViewAnimation.Builder()
                            .durationMillis(500)
                            .inDirection(TranslateViewAnimation.Direction.RIGHT)
                            .outDirection(TranslateViewAnimation.Direction.LEFT)
                            .inView(controller.getView())
                            .outView(getView())
                            .build()
            );
        });
        optionsButton.setPosition(startButton.getPosition().x, startButton.getPosition().y + 72);

        getView().addMenuButton(optionsButton);

        MenuButton instructionsButton = new MenuButton(getView());
        instructionsButton.setText("Instructions");
        instructionsButton.setOnClickListener(widget -> {
            InstructionsController controller = new InstructionsController(getApplication());
            moveToController(controller, new TranslateViewAnimation.Builder()
                    .durationMillis(500)
                    .inView(controller.getView())
                    .outView(getView())
                    .inDirection(TranslateViewAnimation.Direction.RIGHT)
                    .outDirection(TranslateViewAnimation.Direction.LEFT)
                    .build()
            );
        });
        instructionsButton.setPosition(optionsButton.getPosition().x, optionsButton.getPosition().y + 72);

        getView().addMenuButton(instructionsButton);
    }

    private void moveToGame() {
        GameController gameController = new GameController(getApplication());
        moveToController(gameController, new FadeViewAnimation.Builder().durationMillis(500).inView(gameController.getView()).outView(getView()).build());
    }

    @Override
    public MenuView getView() {
        return super.getView();
    }

    @Override
    public void onResume() {
        super.onResume();
        getView().updateViews();
        if (getGameState().isInGame()) {
            moveToGame();
        }
    }
}
