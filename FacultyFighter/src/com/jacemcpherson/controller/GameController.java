package com.jacemcpherson.controller;

import com.jacemcpherson.animation.FadeViewAnimation;
import com.jacemcpherson.animation.TranslateViewAnimation;
import com.jacemcpherson.model.GameModel;
import com.jacemcpherson.util.Console;

import java.awt.event.KeyEvent;

public class GameController extends BaseController {

    public GameController(Application application) {
        super(application);
        setModel(new GameModel(application, this));

        Console.d("Created game controller");
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
                getModel().setPaused(!getModel().isGamePaused());
            }
        } else {
            if (getModel().isGamePaused()) {
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    getModel().saveState();
                }
                if (e.getKeyCode() == KeyEvent.VK_I) {
                    InstructionsController controller = new InstructionsController(getApplication());
                    moveToController(controller, new TranslateViewAnimation.Builder()
                            .durationMillis(500)
                            .inView(controller.getView())
                            .outView(getView())
                            .inDirection(TranslateViewAnimation.Direction.RIGHT)
                            .outDirection(TranslateViewAnimation.Direction.LEFT)
                            .build()
                    );
                }
                if (e.getKeyCode() == KeyEvent.VK_Q) {
                    getModel().setPaused(false);
                    getModel().endGame(null);
                }
            }
            getModel().removeKeyPressed(e.getKeyCode());
        }
    }
}
