package com.jacemcpherson.graphics;

import com.jacemcpherson.util.MathUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Draw {

    public static void drawShape(Graphics g, ShapeDrawingOptions options) {
        Point drawPoint;

        switch (options.shape) {
            case CIRCLE:
                drawPoint = getShapeDrawPosition(options);

                if (options.filled) {
                    g.setColor(options.fillColor);
                    g.fillOval(drawPoint.x, drawPoint.y, (int) options.width, (int) options.height);
                }

                if (options.stroked) {
                    g.setColor(options.strokeColor);
                    g.drawOval(drawPoint.x, drawPoint.y, (int) options.width, (int) options.height);
                }

                break;
            case RECTANGLE:
                drawPoint = getShapeDrawPosition(options);

                if (options.filled) {
                    g.setColor(options.fillColor);
                    g.fillRect(drawPoint.x, drawPoint.y, (int) options.width, (int) options.height);
                }

                if (options.stroked) {
                    g.setColor(options.strokeColor);
                    g.fillRect(drawPoint.x, drawPoint.y, (int) options.width, (int) options.height);
                }

                break;
            case POLYGON:
                if (options.filled) {
                    g.setColor(options.fillColor);
                    g.fillPolygon(options.xPoints, options.yPoints, options.xPoints.length);
                }

                if (options.stroked) {
                    g.setColor(options.strokeColor);
                    g.drawPolygon(options.xPoints, options.yPoints, options.xPoints.length);
                }
                break;
        }
    }

    public static void drawText(Graphics g, String text, Sizeable view, TextDrawingOptions options) {
        g.setFont(options.font);
        Point position = (options.position != null) ? options.position : getTextDrawPosition(g, text, view, options);

        g.setColor(options.color);
        g.drawString(text, position.x, position.y);
    }

    private static Point getShapeDrawPosition(ShapeDrawingOptions options) {
        Point location = (options.position != null) ? options.position : new Point();

        switch (options.drawLocation) {
            case CENTERED:
                location.x -= Math.round(options.width / 2);
                location.y -= Math.round(options.height / 2);
                break;
            case TOP_RIGHT:
                location.x -= options.width;
                break;
            case BOTTOM_LEFT:
                location.y -= options.height;
                break;
            case BOTTOM_RIGHT:
                location.x -= options.width;
                location.y -= options.height;
                break;
            case TOP_LEFT:
            default:
                break;
        }

        return location;
    }

    private static Point getTextDrawPosition(Graphics g, String text, Sizeable view, TextDrawingOptions options) {
        int x = 0, y = 0;

        int textSize;

        // calculate x (horizontal) position
        switch (options.horizontalTextPosition) {
            case CENTER:
                textSize = getTextDrawWidth(g, text);
                x = view.getWidth() / 2 - textSize / 2;
                break;
            case LEFT:
                x = options.paddingLeft;
                break;
            case RIGHT:
                textSize = getTextDrawWidth(g, text);
                x = view.getWidth() - textSize - options.paddingRight;
                break;
        }

        switch (options.verticalTextPosition) {
            case CENTER:
                y = view.getHeight() / 2 + options.font.getSize() / 3;
                break;
            case TOP:
                y = options.font.getSize() + options.paddingTop;
                break;
            case BOTTOM:
                y = view.getHeight() - options.paddingBottom;
                break;
        }

        int horiShift = -1 * options.shiftLeft + options.shiftRight;
        int vertShift = -1 * options.shiftUp + options.shiftDown;

        return new Point(x + horiShift, y + vertShift);
    }

    public static int getTextDrawWidth(Graphics g, String stringToMeasure, Font font) {
        return g.getFontMetrics(font).stringWidth(stringToMeasure);
    }

    public static int getTextDrawWidth(Graphics g, String stringToMeasure) {
        return getTextDrawWidth(g, stringToMeasure, g.getFont());
    }


    public static void drawImage(Graphics g, BufferedImage image, int x, int y) {
        drawImage(g, image, x, y, image.getWidth(), image.getHeight());
    }

    public static void drawImage(Graphics g, BufferedImage image, int x, int y, int width, int height) {
        g.drawImage(image, x, y, width, height, null);
    }

    public static void drawImage(Graphics g, BufferedImage image, Sizeable view, ImageDrawingOptions options) {
        int width = options.width;
        int height = options.height;

        Dimension d = MathUtil.getScaled(image, width, height);

        width = (int)d.getWidth();
        height = (int)d.getHeight();

        if (options.position != null) {
            drawImage(g, image, options.position.x, options.position.y, width, height);
            return;
        }

        int x = 0;
        int y = 0;

        switch (options.veritcalImagePosition) {
            case TOP:
                y = options.paddingTop;
                break;
            case CENTER:
                y = view.getHeight() / 2 - height / 2;
                break;
            case BOTTOM:
                y = view.getHeight() - height - options.paddingBottom;
                break;
        }

        switch (options.horizontalImagePosition) {
            case LEFT:
                x = options.paddingLeft;
                break;
            case CENTER:
                x = view.getWidth() / 2 - width / 2;
                break;
            case RIGHT:
                x = view.getWidth() - width - options.paddingRight;
                break;
        }

        int vertShift = -1 * options.shiftLeft + options.shiftRight;
        int horiShift = -1 * options.shiftUp + options.shiftDown;

        drawImage(g, image, x + horiShift, y + vertShift, width, height);
    }
}
