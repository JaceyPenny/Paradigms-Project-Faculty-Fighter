package com.jacemcpherson.util;

import com.jacemcpherson.resources.R;

import java.awt.*;
import java.io.File;

public class FontUtil {

    private static Font mFont;

    private static void initializeFont() {
        if (mFont != null) return;

        try {
            // load game font here
            GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            mFont = Font.createFont(Font.TRUETYPE_FONT, new File(R.asset.font_airstrike));
            graphicsEnvironment.registerFont(mFont);
        } catch (Exception e) {
            mFont = new Font("TimesRoman", Font.PLAIN, 16);
        }
    }

    public static Font gameFont() {
        return gameFont(18f);
    }

    public static Font gameFont(float size) {
        return gameFont(Font.PLAIN, size);
    }

    public static Font gameFont(int type, float size) {
        initializeFont();
        return mFont.deriveFont(type).deriveFont(size);
    }

}
