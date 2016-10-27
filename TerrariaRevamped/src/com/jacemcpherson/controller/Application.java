package com.jacemcpherson.controller;

import com.jacemcpherson.util.Console;

public class Application {

    private GameWindow mWindow;

    Application() throws Exception {
        Runtime.getRuntime().addShutdownHook( new Thread( () -> {
            Console.i("Closing");
        }));
    }

    public void initWindow() {
        mWindow = new GameWindow(this);
        mWindow.setVisible(true);
    }

    public GameWindow getGameWindow() {
        return mWindow;
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
