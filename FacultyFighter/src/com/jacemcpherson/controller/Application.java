package com.jacemcpherson.controller;

import com.jacemcpherson.resources.GameState;
import com.jacemcpherson.util.Console;
import com.jacemcpherson.util.FileUtil;

public class Application {

    private GameWindow mWindow;

    private GameState mGameState = new GameState();

    Application() throws Exception {
        Runtime.getRuntime().addShutdownHook(new Thread(
                () -> Console.d("Closing")
        ));
        loadGameState();
    }

    public void loadGameState() {
        mGameState = FileUtil.loadGameState();
    }

    public void saveGameState() {
        FileUtil.saveGameState(mGameState);
        Console.i("Game has been saved successfully.");
    }

    public void initWindow() {
        mWindow = new GameWindow(this);
    }

    public GameWindow getGameWindow() {
        return mWindow;
    }

    public GameState getGameState() {
        return mGameState;
    }

    /** MAIN METHOD */
    public static void main(String[] args) {
        // write your code here
        Console.init();

        try {
            Application app = new Application();
            app.initWindow();
        } catch (Exception e) {
            Console.e("Could not load Terraria Paradigms.");
            Console.exception(e);
            return;
        }

        while (Console.hasNext()) {
            String input = Console.getLine();
            Console.i(input);
        }
    }

}
