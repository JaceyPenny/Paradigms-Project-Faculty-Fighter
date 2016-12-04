package com.jacemcpherson.util;

import com.jacemcpherson.animation.ViewAnimation;
import com.jacemcpherson.graphics.Sizeable;

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
}
