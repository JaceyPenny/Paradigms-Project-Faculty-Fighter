package com.jacemcpherson.resources;

import com.jacemcpherson.graphics.Enemy;
import com.jacemcpherson.graphics.Player;

public class GameState {

    public static final int DIFFICULTY_EASY = 0;
    public static final int DIFFICULTY_MEDI = 1;
    public static final int DIFFICULTY_HARD = 2;

    int enemySelection = 0;
    int difficulty = DIFFICULTY_EASY;
    int gamesWon = 0;
    int gamesLost = 0;
    Player player;
    Enemy enemy;
    boolean inGame = false;

    public void setEnemySelection(int selection) {
        enemySelection = selection;
    }

    public int getEnemySelection() {
        return enemySelection;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setGamesWon(int won) {
        gamesWon = won;
    }

    public void incrementGamesWon() {
        gamesWon++;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesLost(int lost) {
        gamesLost = lost;
    }

    public void incrementGamesLost() {
        gamesLost++;
    }

    public int getGamesLost() {
        return gamesLost;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public boolean isInGame() {
        return inGame;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public Enemy getEnemy() {
        return enemy;
    }
}
