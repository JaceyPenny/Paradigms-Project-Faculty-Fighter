package com.jacemcpherson.controller;

import com.jacemcpherson.util.Console;

public class GameState {

    int enemySelection = 0;

    public GameState() {

    }

    public void saveGameState() {

    }

    public void loadGameState() {

    }

    public void setEnemySelection(int selection) {
        Console.d("Enemy selection is %d", selection);
        enemySelection = selection;
    }

    public int getEnemySelection() {
        return enemySelection;
    }

}
