package com.jacemcpherson.controller;

import com.jacemcpherson.animation.TranslateViewAnimation;
import com.jacemcpherson.animation.ViewAnimation;
import com.jacemcpherson.model.SplashModel;
import com.jacemcpherson.util.Console;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static com.jacemcpherson.animation.TranslateViewAnimation.Direction.LEFT;
import static com.jacemcpherson.animation.TranslateViewAnimation.Direction.RIGHT;

public class SplashController extends BaseController {


    public SplashController(Application application) {
        super(application);
        setModel(new SplashModel(application));
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        try {
            MenuController controller = new MenuController(getApplication());
            ViewAnimation viewAnimation = new TranslateViewAnimation.Builder()
                    .outDirection(LEFT)
                    .inDirection(RIGHT)
                    .interpolator(TranslateViewAnimation.Interpolator.ACCELERATE_DECELERATE)
                    .durationFrames(1* ViewAnimation.GLOBAL_FPS)
                    .outView(getView())
                    .inView(controller.getView())
                    .build();
//            ViewAnimation viewAnimation = new FadeViewAnimation.Builder()
//                    .durationSeconds(2)
//                    .outView(getView())
//                    .inView(controller.getView())
//                    .build();
            moveToController(controller, viewAnimation);
        } catch (ViewAnimation.InvalidAnimationException ex) {
            Console.exception(ex);
        }
    }

}
