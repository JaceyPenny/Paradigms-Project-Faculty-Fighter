package com.jacemcpherson.controller;

import com.jacemcpherson.animation.TranslateViewAnimation;
import com.jacemcpherson.animation.ViewAnimation;
import com.jacemcpherson.model.SplashModel;
import com.jacemcpherson.util.Console;

import java.awt.event.MouseEvent;

import static com.jacemcpherson.animation.TranslateViewAnimation.Direction.LEFT;
import static com.jacemcpherson.animation.TranslateViewAnimation.Direction.RIGHT;

public class SplashController extends BaseController {


    public SplashController(Application application) {
        super(application);
        setModel(new SplashModel(application));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        try {
            MenuController controller = new MenuController(getApplication());
            ViewAnimation animation = new TranslateViewAnimation.Builder()
                    .outDirection(LEFT)
                    .inDirection(RIGHT)
                    .durationMillis(300)
                    .outView(getView())
                    .inView(controller.getView())
                    .build();
            moveToController(controller, animation);
        } catch (Exception ex) {
            Console.exception(ex);
        }
    }

}
