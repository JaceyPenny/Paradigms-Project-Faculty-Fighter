package com.jacemcpherson.graphics;

import com.jacemcpherson.resources.Pair;

public class Enemy extends Player {

    public Enemy(int width, int height, Pair<String, String> pair) {
        super(width, height, pair.getFirst());
        setName(pair.getSecond());
        flipPlayer();
    }
}
