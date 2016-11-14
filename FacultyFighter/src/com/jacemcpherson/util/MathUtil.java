package com.jacemcpherson.util;

import com.jacemcpherson.animation.ViewAnimation;

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
}
