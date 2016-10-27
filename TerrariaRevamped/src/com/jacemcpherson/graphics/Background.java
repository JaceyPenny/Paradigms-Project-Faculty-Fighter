package com.jacemcpherson.graphics;

import com.jacemcpherson.view.BaseView;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.jacemcpherson.graphics.Background.BackgroundType.*;


public class Background {
    enum BackgroundType {
        IMAGE, IMAGE_TESS, COLOR, EMPTY
    }

    Color mDefaultBackgroundColor = Color.white;

    BackgroundType mType = EMPTY;
    BufferedImage mImage;
    Color mColor;


    public Background(BufferedImage image, boolean tesselated) {
        mColor = null;
        mImage = image;
        mType = tesselated ? IMAGE_TESS : IMAGE;
    }

    public Background(BufferedImage image, boolean tesselated, Color defaultColor) {
        this(image, tesselated);
        mDefaultBackgroundColor = defaultColor;
    }

    public Background(Color color) {
        mColor = color;
        mImage = null;
        mType = COLOR;
    }

    public Background(int color) {
        this(new Color(color));
    }

    public void draw(Graphics g, BaseView onView) {
        switch (mType) {
            case COLOR:
                g.fillRect(0, 0, onView.getWidth(), onView.getHeight());
                break;
            case IMAGE:
                g.drawImage(mImage, 0, 0, mImage.getWidth(), mImage.getHeight(), new Color(0x00FFFFFF), null);
                break;
            case IMAGE_TESS:
                int compWidth = onView.getWidth();
                int compHeight = onView.getHeight();
                int width = mImage.getWidth();
                int height = mImage.getHeight();

                for (int x = 0; x < compWidth; x += width) {
                    for (int y = 0; y <= compHeight; y += height) {
                        g.drawImage(mImage, x, y, width, height, mDefaultBackgroundColor, null);
                    }
                }
                break;
            default:
                break;
        }
    }

}
