package com.jacemcpherson.graphics;

import com.jacemcpherson.resources.Pair;

import java.awt.event.KeyEvent;

public class Enemy extends Player {

    public Enemy(int width, int height, Pair<String, String> pair) {
        super(width, height, pair.getFirst());
        setName(pair.getSecond());
        flipPlayer();
    }

    public void stopPunch() {
        removeKeyPressed(KeyEvent.VK_SPACE);
    }

    public void punch() {
        clearKeys();
        addKeyPressed(KeyEvent.VK_SPACE);
    }

    public void stopBlock() {
        removeKeyPressed(KeyEvent.VK_S);
    }

    public void block() {
        addKeyPressed(KeyEvent.VK_S);
    }

    public void walkLeft() {
        removeKeyPressed(KeyEvent.VK_D);
        addKeyPressed(KeyEvent.VK_A);
    }

    public void walkRight() {
        removeKeyPressed(KeyEvent.VK_A);
        addKeyPressed(KeyEvent.VK_D);
    }

    public void freeze() {
        clearKeys();
    }
}
