package com.jacemcpherson.graphics;

import com.jacemcpherson.view.BaseView;

import java.awt.*;

public class Draw {

    public static void drawText(Graphics g, String text, BaseView view, TextDrawingOptions options) {
        g.setFont(options.font);
        Point location = getDrawLocation(g, text, view, options);

        g.setColor(options.color);
        g.drawString(text, location.x, location.y);
    }

    private static Point getDrawLocation(Graphics g, String text, BaseView view, TextDrawingOptions options) {
        int x = 0, y = 0;

        int textSize = getTextDrawWidth(g, text);

        // calculate x (horizontal) position
        switch (options.horizontalTextPosition) {
            case CENTER:
                x = view.getWidth() / 2 - textSize / 2;
                break;
            case LEFT:
                x = options.paddingLeft;
                break;
            case RIGHT:
                x = view.getWidth() - textSize - options.paddingRight;
                break;
        }

        switch (options.verticalTextPosition) {
            case CENTER:
                y = view.getHeight() / 2 + options.font.getSize() / 2;
                break;
            case TOP:
                y = options.font.getSize() + options.paddingTop;
                break;
            case BOTTOM:
                y = view.getHeight() - options.paddingBottom;
                break;
        }

        return new Point(x, y);
    }

    public static int getTextDrawWidth(Graphics g, String stringToMeasure) {
        return g.getFontMetrics().stringWidth(stringToMeasure);
    }

}
