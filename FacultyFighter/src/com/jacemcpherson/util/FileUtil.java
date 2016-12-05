package com.jacemcpherson.util;

import com.jacemcpherson.resources.GameState;
import com.jacemcpherson.graphics.Enemy;
import com.jacemcpherson.graphics.Player;
import com.jacemcpherson.resources.R;
import com.jacemcpherson.resources.Resources;

import java.io.*;
import java.util.ArrayList;

public class FileUtil {

    public static void saveGameState(GameState state) {
        ArrayList<String> lines = new ArrayList<>();

        if (!state.isInGame()) {
            lines.add("0");
            lines.add("" + state.getDifficulty());
            lines.add("" + state.getGamesWon() + ";" + state.getGamesLost());
        } else {
            lines.add("1");
            lines.add("" + state.getDifficulty());
            lines.add("" + state.getGamesWon() + ";" + state.getGamesLost());
            lines.add("" + state.getEnemySelection());
            lines.add(state.getPlayer().serialize());
            lines.add(state.getEnemy().serialize());
        }

        writeToFile(new File(R.asset.game_state), lines);
    }

    public static GameState loadGameState() {
        File gameStateFile = new File(R.asset.game_state);

        GameState state = new GameState();

        if (gameStateFile.exists()) {
            ArrayList<String> lines = readFromFile(gameStateFile);

            if (lines.get(0).equals("0")) { // not in game
                state.setInGame(false);
                state.setDifficulty(Integer.parseInt(lines.get(1)));
                String[] games = lines.get(2).split(";");
                state.setGamesWon(Integer.parseInt(games[0]));
                state.setGamesLost(Integer.parseInt(games[1]));
            } else {
                state.setInGame(true);
                state.setDifficulty(Integer.parseInt(lines.get(1)));
                String[] games = lines.get(2).split(";");
                state.setGamesWon(Integer.parseInt(games[0]));
                state.setGamesLost(Integer.parseInt(games[1]));
                int enemyNum = Integer.parseInt(lines.get(3));
                state.setEnemySelection(enemyNum);

                // Player data
                String[] data = lines.get(4).split(";");
                int x = Integer.parseInt(data[0]);
                int y = Integer.parseInt(data[1]);
                Player.PlayerDirection dir = (Integer.parseInt(data[2]) == 0) ? Player.PlayerDirection.LEFT : Player.PlayerDirection.RIGHT;
                int health = Integer.parseInt(data[3]);

                Player player = new Player(-1, 180);
                player.setPosition(x, y);
                player.setDirection(dir);
                player.setHealth(health);

                // enemy data
                data = lines.get(5).split(";");
                x = Integer.parseInt(data[0]);
                y = Integer.parseInt(data[1]);
                dir = (Integer.parseInt(data[2]) == 0) ? Player.PlayerDirection.LEFT : Player.PlayerDirection.RIGHT;
                health = Integer.parseInt(data[3]);

                Enemy enemy = new Enemy(-1, 180, Resources.getFacultyFiles().get(enemyNum));
                enemy.setPosition(x, y);
                enemy.setDirection(dir);
                enemy.setHealth(health);

                state.setPlayer(player);
                state.setEnemy(enemy);
            }
        }

        return state;
    }

    public static ArrayList<String> readFromFile(File file) {
        ArrayList<String> output = new ArrayList<>();

        try {
            FileReader reader = new FileReader(file);

            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                output.add(line);
            }

            bufferedReader.close();
        } catch (IOException e) {

        }

        return output;
    }

    public static void writeToFile(File file, ArrayList<String> lines) {
        try {
            FileWriter writer = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            for (String line : lines) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        } catch (IOException e) {

        }
    }

}
