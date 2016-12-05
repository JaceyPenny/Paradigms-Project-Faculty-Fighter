package com.jacemcpherson.model;

import com.jacemcpherson.controller.Application;
import com.jacemcpherson.controller.BaseController;
import com.jacemcpherson.graphics.Enemy;
import com.jacemcpherson.graphics.Player;
import com.jacemcpherson.resources.Resources;
import com.jacemcpherson.task.EnemyAI;
import com.jacemcpherson.view.GameView;

public class GameModel extends BaseModel {

    Player mPlayer;
    Enemy mEnemy;

    boolean gameOver = false;
    boolean gamePaused = false;

    EnemyAI enemyAI;

    public GameModel(Application application, BaseController controller) {
        super(application, controller);
        setView(new GameView(application, this));

        // we are loading from a file
        if (getGameState().isInGame()) {
            mPlayer = getGameState().getPlayer();
            mEnemy = getGameState().getEnemy();
            togglePause();
        } else {
            mPlayer = new Player(-1, 180);
            mPlayer.setPosition(0, 220);

            mEnemy = new Enemy(-1, 180, Resources.getFacultyFiles().get(getGameState().getEnemySelection()));
            mEnemy.setPosition(480, 220);

            getGameState().setPlayer(mPlayer);
            getGameState().setEnemy(mEnemy);
        }

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

        enemyAI = new EnemyAI(mPlayer, mEnemy, getGameState().getDifficulty());
        enemyAI.setPaused(isGamePaused());
        Thread thread = new Thread(enemyAI);
        thread.start();

        getGameState().setInGame(true);
    }

    public void togglePause() {
        gamePaused = !gamePaused;
        if (gamePaused) {
            mPlayer.clearKeys();
            mEnemy.clearKeys();
            if (enemyAI != null)
                enemyAI.setPaused(true);
        } else {
            if (enemyAI != null)
                enemyAI.setPaused(false);
        }
        getView().togglePause();
    }

    public void endGame(Player winner) {
        enemyAI.stop();
        mPlayer.clearKeys();
        mEnemy.clearKeys();
        gameOver = true;

        String winningText;

        if (winner == mPlayer) {
            getGameState().incrementGamesWon();
            winningText = "You win!";
        } else {
            getGameState().incrementGamesLost();
            winningText = winner.getName() + " wins";
        }

        getView().setGameWon(winningText);


        getGameState().setInGame(false);
    }

    public void saveState() {
        getApplication().saveGameState();
        getView().setSaved();
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
