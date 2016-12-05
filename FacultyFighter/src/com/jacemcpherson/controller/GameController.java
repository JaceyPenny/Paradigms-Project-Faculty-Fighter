package com.jacemcpherson.controller;

import com.jacemcpherson.animation.FadeViewAnimation;
import com.jacemcpherson.model.GameModel;

import java.awt.event.KeyEvent;

public class GameController extends BaseController {

    public GameController(Application application) {
        super(application);
        setModel(new GameModel(application, this));
    }

    @Override
    public GameModel getModel() {
        return super.getModel();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() != KeyEvent.VK_ESCAPE) {
            getModel().addKeyPressed(e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if (getModel().isGameOver()) {
                popController(
                        new FadeViewAnimation.Builder()
                                .durationMillis(500)
                                .outView(getView())
                                .inView(getPreviousController().getView())
                                .build()
                );
            } else {
                getModel().togglePause();
            }
        } else {
            getModel().removeKeyPressed(e.getKeyCode());
        }
    }
}
