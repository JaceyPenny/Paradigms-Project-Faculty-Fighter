package com.jacemcpherson.view;

import com.jacemcpherson.controller.Application;
import com.jacemcpherson.graphics.*;
import com.jacemcpherson.model.BaseModel;
import com.jacemcpherson.model.GameModel;
import com.jacemcpherson.resources.R;
import com.jacemcpherson.util.FontUtil;
import com.jacemcpherson.util.ImageUtil;
import com.jacemcpherson.widget.ImageWidget;

import java.awt.*;

public class GameView extends BaseView {

    int mHealthBarLength;

    float mHealth;
    float mEnemyHealth;

    String mEnemyName = "";

    boolean gameWon = false;
    boolean gamePaused = false;
    boolean gameSaved = false;
    String winningText = "Winner: ";

    public GameView(Application application, BaseModel model) {
        super(application, model);
        setBackground(new Background(ImageUtil.loadImageSynchronous(R.image.stage)));
        mHealthBarLength = getWidth() / 2 - 80;
        mHealth = 0.33f;
        mEnemyHealth = 0.33f;

        ImageWidget healthBG = new ImageWidget(this, ImageUtil.loadImageSynchronous(R.image.menu_button_pressed));
        healthBG.setWidth(getWidth() + 40);
        healthBG.setHeight(80);
        healthBG.setPosition(-20, 0);
        addWidget(healthBG);
    }

    public void updateView(GameModel model) {
        Player player = model.getPlayer();
        Enemy enemy = model.getEnemy();
        mHealth = player.getHealth() / 100f;
        mEnemyName = enemy.getName();
        mEnemyHealth = enemy.getHealth() / 100f;
    }

    public void setGameWon(String winningText) {
        gameWon = true;
        this.winningText = winningText;
    }

    public void setSaved() {
        gameSaved = true;
    }

    public void togglePause() {
        gameSaved = false;
        gamePaused = !gamePaused;
    }

    @Override
    public void paint(Graphics gr) {
        super.paint(gr);

        updateView(getModel());

        Graphics2D g = (Graphics2D) gr;

        // draw player health bar
        g.setColor(Color.green);
        g.fillRect(40, 20, (int) (mHealth * mHealthBarLength), 20);

        g.setColor(Color.black);
        g.setStroke(new BasicStroke(3));
        g.drawRect(40, 20, mHealthBarLength, 20);

        Draw.drawText(g, "Health", this,
                new TextDrawingOptions()
                        .color(Color.black)
                        .position(40, 55)
                        .font(FontUtil.gameFont(16f))
        );

        // draw enemy health bar
        g.setColor(Color.red);
        int fullBarStart = getWidth() - 40 - mHealthBarLength;
        int healthLength = (int) (mEnemyHealth * mHealthBarLength);
        int startX = fullBarStart + (mHealthBarLength - healthLength);

        g.fillRect(startX, 20, healthLength, 20);
        g.setColor(Color.black);
        g.drawRect(fullBarStart, 20, mHealthBarLength, 20);

        Draw.drawText(g, mEnemyName, this,
                new TextDrawingOptions()
                        .color(Color.black)
                        .horizontalPosition(TextDrawingOptions.HorizontalTextPosition.RIGHT)
                        .verticalPosition(TextDrawingOptions.VerticalTextPosition.TOP)
                        .padding(0, 40, 40, 0)
                        .font(FontUtil.gameFont(16f))
        );

        Draw.drawText(g, "VS", this,
                new TextDrawingOptions()
                        .color(Color.black)
                        .horizontalPosition(TextDrawingOptions.HorizontalTextPosition.CENTER)
                        .verticalPosition(TextDrawingOptions.VerticalTextPosition.TOP)
                        .padding(0, 10, 0, 0)
                        .shiftLeft(3)
                        .font(FontUtil.gameFont(36f))
        );



        if (gamePaused) {
            g.setColor(new Color(0, 0, 0, 180));
            g.fillRect(0, 0, getWidth(), getHeight());
            Draw.drawText(g, "Paused", this,
                    new TextDrawingOptions()
                            .color(Color.white)
                            .horizontalPosition(TextDrawingOptions.HorizontalTextPosition.CENTER)
                            .verticalPosition(TextDrawingOptions.VerticalTextPosition.CENTER)
                            .font(FontUtil.gameFont(48f))
            );

            Draw.drawText(g, gameSaved ? "Saved!" : "Press 'S' to save game", this,
                    new TextDrawingOptions()
                            .color(Color.white)
                            .horizontalPosition(TextDrawingOptions.HorizontalTextPosition.CENTER)
                            .verticalPosition(TextDrawingOptions.VerticalTextPosition.CENTER)
                            .shiftDown(36)
                            .font(FontUtil.gameFont(24f))
            );

            Draw.drawText(g, "Press 'I' for instructions", this,
                    new TextDrawingOptions()
                            .color(Color.white)
                            .horizontalPosition(TextDrawingOptions.HorizontalTextPosition.CENTER)
                            .verticalPosition(TextDrawingOptions.VerticalTextPosition.CENTER)
                            .shiftDown(56)
                            .font(FontUtil.gameFont(24f))
            );
        } else {
            Draw.drawText(g, "Press ESC to pause", this,
                    new TextDrawingOptions()
                            .color(Color.lightGray)
                            .horizontalPosition(TextDrawingOptions.HorizontalTextPosition.LEFT)
                            .verticalPosition(TextDrawingOptions.VerticalTextPosition.TOP)
                            .padding(10, 70, 0, 0)
                            .font(FontUtil.gameFont(18f))
            );
        }

        if (gameWon) {
            g.setColor(new Color(0, 0, 0, 180));
            g.fillRect(0, 0, getWidth(), getHeight());
            Draw.drawText(g, winningText, this,
                    new TextDrawingOptions()
                            .color(Color.white)
                            .horizontalPosition(TextDrawingOptions.HorizontalTextPosition.CENTER)
                            .verticalPosition(TextDrawingOptions.VerticalTextPosition.CENTER)
                            .font(FontUtil.gameFont(48f))
            );

            int won = getModel().getGameState().getGamesWon();
            int lost = getModel().getGameState().getGamesLost();

            Draw.drawText(g, "You've won " + won + " games and lost " + lost, this,
                    new TextDrawingOptions()
                            .color(Color.white)
                            .horizontalPosition(TextDrawingOptions.HorizontalTextPosition.CENTER)
                            .verticalPosition(TextDrawingOptions.VerticalTextPosition.CENTER)
                            .shiftDown(30)
                            .font(FontUtil.gameFont(24f))
            );

            Draw.drawText(g, "Press ESC to return home", this,
                    new TextDrawingOptions()
                            .color(Color.lightGray)
                            .horizontalPosition(TextDrawingOptions.HorizontalTextPosition.CENTER)
                            .verticalPosition(TextDrawingOptions.VerticalTextPosition.CENTER)
                            .shiftDown(60)
                            .font(FontUtil.gameFont(24f))
            );
        }
    }
}
