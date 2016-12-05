package com.jacemcpherson.view;

import com.jacemcpherson.controller.Application;
import com.jacemcpherson.graphics.Draw;
import com.jacemcpherson.graphics.TextDrawingOptions;
import com.jacemcpherson.model.BaseModel;
import com.jacemcpherson.util.FontUtil;

import java.awt.*;

public class InstructionsView extends BaseView {

    public InstructionsView(Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Draw.drawText(g, "Welcome to Faculty Fighter", this,
                new TextDrawingOptions()
                        .color(Color.white)
                        .horizontalPosition(TextDrawingOptions.HorizontalTextPosition.CENTER)
                        .verticalPosition(TextDrawingOptions.VerticalTextPosition.TOP)
                        .font(FontUtil.gameFont(32f))
        );

        Draw.drawText(g, "You are Dr. Patitz on the quest to beat them all.", this,
                new TextDrawingOptions()
                        .color(Color.white)
                        .horizontalPosition(TextDrawingOptions.HorizontalTextPosition.LEFT)
                        .verticalPosition(TextDrawingOptions.VerticalTextPosition.TOP)
                        .shiftDown(40)
                        .font(FontUtil.gameFont(20f))
        );

        Draw.drawText(g, "You should use 'A' and 'D' to move Left and Right.", this,
                new TextDrawingOptions()
                        .color(Color.green)
                        .horizontalPosition(TextDrawingOptions.HorizontalTextPosition.LEFT)
                        .verticalPosition(TextDrawingOptions.VerticalTextPosition.TOP)
                        .shiftDown(65)
                        .font(FontUtil.gameFont(20f))
        );

        Draw.drawText(g, "You should use 'S' to block the opponent's hits.", this,
                new TextDrawingOptions()
                        .color(Color.blue)
                        .horizontalPosition(TextDrawingOptions.HorizontalTextPosition.LEFT)
                        .verticalPosition(TextDrawingOptions.VerticalTextPosition.TOP)
                        .shiftDown(90)
                        .font(FontUtil.gameFont(20f))
        );

        Draw.drawText(g, "You should use 'SPACE' to punch the enemy.", this,
                new TextDrawingOptions()
                        .color(Color.red)
                        .horizontalPosition(TextDrawingOptions.HorizontalTextPosition.LEFT)
                        .verticalPosition(TextDrawingOptions.VerticalTextPosition.TOP)
                        .shiftDown(115)
                        .font(FontUtil.gameFont(20f))
        );

        Draw.drawText(g, "Every punch you land deals 10 damage out of 100", this,
                new TextDrawingOptions()
                        .color(Color.white)
                        .horizontalPosition(TextDrawingOptions.HorizontalTextPosition.LEFT)
                        .verticalPosition(TextDrawingOptions.VerticalTextPosition.TOP)
                        .shiftDown(140)
                        .font(FontUtil.gameFont(20f))
        );

        Draw.drawText(g, "The first to drain their opponent's health wins!", this,
                new TextDrawingOptions()
                        .color(Color.white)
                        .horizontalPosition(TextDrawingOptions.HorizontalTextPosition.LEFT)
                        .verticalPosition(TextDrawingOptions.VerticalTextPosition.TOP)
                        .shiftDown(165)
                        .font(FontUtil.gameFont(20f))
        );

        Draw.drawText(g, "At any time during the fight, press 'ESC' to pause.", this,
                new TextDrawingOptions()
                        .color(Color.yellow)
                        .horizontalPosition(TextDrawingOptions.HorizontalTextPosition.LEFT)
                        .verticalPosition(TextDrawingOptions.VerticalTextPosition.TOP)
                        .shiftDown(190)
                        .font(FontUtil.gameFont(20f))
        );

        Draw.drawText(g, "While paused, you can save the game state with 'S'", this,
                new TextDrawingOptions()
                        .color(Color.green)
                        .horizontalPosition(TextDrawingOptions.HorizontalTextPosition.LEFT)
                        .verticalPosition(TextDrawingOptions.VerticalTextPosition.TOP)
                        .shiftDown(215)
                        .font(FontUtil.gameFont(20f))
        );

        Draw.drawText(g, "Do you have what it takes to defeat", this,
                new TextDrawingOptions()
                        .color(Color.white)
                        .horizontalPosition(TextDrawingOptions.HorizontalTextPosition.CENTER)
                        .verticalPosition(TextDrawingOptions.VerticalTextPosition.TOP)
                        .shiftDown(250)
                        .font(FontUtil.gameFont(28f))
        );

        Draw.drawText(g, "the entire CSCE faculty?", this,
                new TextDrawingOptions()
                        .color(Color.white)
                        .horizontalPosition(TextDrawingOptions.HorizontalTextPosition.CENTER)
                        .verticalPosition(TextDrawingOptions.VerticalTextPosition.TOP)
                        .shiftDown(275)
                        .font(FontUtil.gameFont(28f))
        );
    }
}
