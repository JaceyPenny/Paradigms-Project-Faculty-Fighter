package com.jacemcpherson.util;

import com.jacemcpherson.animation.ViewAnimation;
import com.jacemcpherson.graphics.Sizeable;
import com.jacemcpherson.widget.BaseWidget;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MathUtil {

    public static double map(double x, double inMin, double inMax, double outMin, double outMax)
    {
        return (x - inMin) * (outMax - outMin) / (inMax - inMin) + outMin;
    }

    public static int millisToFrames(int durationMs) {
        return (int) ((double)durationMs / (1000.0 / ViewAnimation.GLOBAL_FPS));
    }

    public static int secondsToFrames(int seconds) {
        return seconds * ViewAnimation.GLOBAL_FPS;
    }

    public static int secondsToFrames(double seconds) {
        return (int) Math.round(seconds * ViewAnimation.GLOBAL_FPS);
    }

    public static boolean pointInBounds(Point p, Rectangle r) {
        return r.contains(p);
    }

    public static Dimension getScaled(BufferedImage image, int width, int height) {
        if (width > 0 && height <= 0) {
            height = (int) ((float) image.getHeight() / image.getWidth() * width);
        } else if (height > 0 && width <= 0) {
            width = (int) ((float) image.getWidth() / image.getHeight() * height);
        } else if (height == -1 && width <= 0) {
            width = image.getWidth();
            height = image.getHeight();
        }

        return new Dimension(width, height);
    }

    public static Dimension getScaled(Sizeable view, int width, int height) {
        if (width > 0 && height <= 0) {
            height = (int) ((float) view.getHeight() / view.getWidth() * width);
        } else if (height > 0 && width <= 0) {
            width = (int) ((float) view.getWidth() / view.getHeight() * height);
        } else if (height <= 0 && width <= 0) {
            width = view.getWidth();
            height = view.getHeight();
        }

        return new Dimension(width, height);
    }

    public static Point getPositionInView(Sizeable view, int width, int height, BaseWidget.HorizontalGravity horizontal, BaseWidget.VerticalGravity vertical) {
        int x = 0, y = 0;
        switch (horizontal) {
            case LEFT:
                x = 0;
                break;
            case CENTER:
                x = view.getWidth() / 2 - width / 2;
                break;
            case RIGHT:
                x = view.getWidth() - width;
        }

        switch (vertical) {
            case TOP:
                y = 0;
                break;
            case CENTER:
                y = view.getHeight() / 2 - height / 2;
                break;
            case BOTTOM:
                y = view.getHeight() - height;
        }

        return new Point(x, y);
    }
}
