package com.jacemcpherson.model;

import com.jacemcpherson.controller.Application;
import com.jacemcpherson.controller.BaseController;
import com.jacemcpherson.graphics.Enemy;
import com.jacemcpherson.graphics.Player;
import com.jacemcpherson.resources.Pair;
import com.jacemcpherson.resources.Resources;
import com.jacemcpherson.view.GameView;

public class GameModel extends BaseModel {

    Player mPlayer;
    Enemy mEnemy;

    boolean gameOver = false;
    boolean gamePaused = false;

    public GameModel(Application application, BaseController controller) {
        super(application, controller);
        setView(new GameView(application, this));

        mPlayer = new Player(-1, 180);
        mPlayer.setPosition(0, 220);


        Pair<String, String> enemyInfo = Resources.getFacultyFiles().get(getApplication().getGameState().getEnemySelection());

        mEnemy = new Enemy(-1, 180, enemyInfo);
        mEnemy.setPosition(480, 220);

        mPlayer.setPunchCallback(fromPlayer -> {
            mEnemy.takeHit(fromPlayer);
            if (mEnemy.getHealth() <= 0) {
                endGame(mPlayer);
            }
        });

        mEnemy.setPunchCallback(fromPlayer -> {
            mPlayer.takeHit(fromPlayer);
            if (mPlayer.getHealth() <= 0) {
                endGame(mEnemy);
            }
        });

        getView().addSprite(mPlayer);
        getView().addSprite(mEnemy);
    }

    public void togglePause() {
        gamePaused = !gamePaused;
        if (gamePaused) {
            mPlayer.clearKeys();
            mEnemy.clearKeys();
        }
        getView().togglePause();
    }

    public void endGame(Player winner) {
        mPlayer.clearKeys();
        mEnemy.clearKeys();
        gameOver = true;

        getView().setGameWon(winner);
    }

    public void addKeyPressed(int keyCode) {
        if (!gameOver && !gamePaused)
            mPlayer.addKeyPressed(keyCode);
    }

    public void removeKeyPressed(int keyCode) {
        if (!gameOver && !gamePaused)
            mPlayer.removeKeyPressed(keyCode);
    }

    public Player getPlayer() {
        return mPlayer;
    }

    public Enemy getEnemy() {
        return mEnemy;
    }

    public boolean isGamePaused() {
        return gamePaused;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public GameView getView() {
        return super.getView();
    }
}
