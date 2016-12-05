package com.jacemcpherson.controller;

import com.jacemcpherson.animation.FadeViewAnimation;
import com.jacemcpherson.animation.ViewAnimation;
import com.jacemcpherson.model.SplashModel;
import com.jacemcpherson.util.ImageUtil;
import com.jacemcpherson.util.MathUtil;

public class SplashController extends BaseController {

    boolean mImagesLoaded = false;

    public SplashController(Application application) {
        super(application);
        setModel(new SplashModel(application, this));

        ImageUtil.preloadAllImagesInBackground(((image, e) -> mImagesLoaded = true));
    }

    @Override
    public void update() {
        if (getView().getFrame() > MathUtil.secondsToFrames(0.5) && mImagesLoaded) {
            if (getGameState().isInGame()) {
                moveToMenuWithoutAnimation();
            } else {
                moveToMenu();
            }
        }
    }

    public void moveToMenuWithoutAnimation() {
        MenuController controller = new MenuController(getApplication());
        moveToController(controller);
    }

    public void moveToMenu() {
        try {
            MenuController controller = new MenuController(getApplication());

            ViewAnimation animation = new FadeViewAnimation.Builder()
                    .durationSeconds(1)
                    .outView(getView())
                    .inView(controller.getView())
                    .build();

            moveToController(controller, animation);
        } catch (ViewAnimation.InvalidAnimationException e) {
        }
    }

}
