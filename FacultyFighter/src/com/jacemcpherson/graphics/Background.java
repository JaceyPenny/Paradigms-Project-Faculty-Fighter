package com.jacemcpherson.graphics;

import com.jacemcpherson.view.BaseView;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.jacemcpherson.graphics.Background.BackgroundType.*;


public class Background {
    public enum BackgroundType {
        IMAGE, IMAGE_SCALED, IMAGE_CENTER_FIT, IMAGE_TESS, COLOR, EMPTY
    }

    boolean mWithBackgroundColor = false;
    BackgroundType mType = EMPTY;
    BufferedImage mImage;
    Color mColor;

    public Background(BufferedImage image, BackgroundType type) {
        this(image, type, null);
    }

    public Background(BufferedImage image, BackgroundType type, Color backgroundColor) {
        mImage = image;
        mType = type;
        mColor = backgroundColor;
        mWithBackgroundColor = backgroundColor != null;
    }

    public Background(BufferedImage image) {
        this(image, IMAGE);
    }

    public Background(Color color) {
        mColor = color;
        mImage = null;
        mType = COLOR;
    }

    public Background(int color) {
        this(new Color(color));
    }

    public void draw(BufferedImage image) {
        Graphics g = image.getGraphics();

        int width = 0;
        int height = 0;
        int x = 0;
        int y = 0;

        switch (mType) {
            case COLOR:
                g.fillRect(0, 0, image.getWidth(), image.getHeight());
                break;
            case IMAGE:
                if (mWithBackgroundColor) {
                    g.setColor(mColor);
                    g.fillRect(0, 0, image.getWidth(), image.getHeight());
                }
                g.drawImage(mImage, 0, 0, mImage.getWidth(), mImage.getHeight(), new Color(0x00000000), null);
                break;
            case IMAGE_SCALED:
                width = image.getWidth();
                height = Math.round(mImage.getHeight()  * ((float)width / mImage.getWidth()));

                if (height < image.getHeight()) {
                    height = image.getHeight();
                    width = Math.round(mImage.getHeight() * ((float)height / mImage.getHeight()));
                }

                g.drawImage(mImage, 0, 0, width, height, new Color(0x00FFFFFF), null);
                break;
            case IMAGE_TESS:
                int compWidth = image.getWidth();
                int compHeight = image.getHeight();
                width = mImage.getWidth();
                height = mImage.getHeight();

                for (x = 0; x < compWidth; x += width) {
                    for (y = 0; y <= compHeight; y += height) {
                        g.drawImage(mImage, x, y, width, height, mColor, null);
                    }
                }
                break;
            case IMAGE_CENTER_FIT:
                float imageRatio = (float) mImage.getWidth() / mImage.getHeight();
                float canvasRatio = (float) image.getWidth() / image.getHeight();

                if (imageRatio > canvasRatio) {
                    width = image.getWidth();
                    height = Math.round(width / imageRatio);
                    x = 0;
                    y = image.getHeight() / 2 - height / 2;
                } else {
                    height = image.getHeight();
                    width = Math.round(height * imageRatio);
                    y = 0;
                    x = image.getWidth() / 2 - width / 2;
                }

                if (mWithBackgroundColor) {
                    g.setColor(mColor);
                    g.fillRect(0, 0, image.getWidth(), image.getHeight());
                }

                g.drawImage(mImage, x, y, width, height, mColor, null);

                break;
            default:
                break;
        }
    }

    public void draw(Graphics g, BaseView onView) {
        int width = 0;
        int height = 0;
        int x = 0;
        int y = 0;

        switch (mType) {
            case COLOR:
                g.fillRect(0, 0, onView.getWidth(), onView.getHeight());
                break;
            case IMAGE:
                if (mWithBackgroundColor) {
                    g.setColor(mColor);
                    g.fillRect(0, 0, onView.getWidth(), onView.getHeight());
                }
                g.drawImage(mImage, 0, 0, mImage.getWidth(), mImage.getHeight(), new Color(0x00000000), null);
                break;
            case IMAGE_SCALED:
                width = onView.getWidth();
                height = Math.round(mImage.getHeight()  * ((float)width / mImage.getWidth()));

                if (height < onView.getHeight()) {
                    height = onView.getHeight();
                    width = Math.round(mImage.getHeight() * ((float)height / mImage.getHeight()));
                }

                g.drawImage(mImage, 0, 0, width, height, new Color(0x00FFFFFF), null);
                break;
            case IMAGE_TESS:
                int compWidth = onView.getWidth();
                int compHeight = onView.getHeight();
                width = mImage.getWidth();
                height = mImage.getHeight();

                for (x = 0; x < compWidth; x += width) {
                    for (y = 0; y <= compHeight; y += height) {
                        g.drawImage(mImage, x, y, width, height, mColor, null);
                    }
                }
                break;
            case IMAGE_CENTER_FIT:
                float imageRatio = (float) mImage.getWidth() / mImage.getHeight();
                float canvasRatio = (float) onView.getWidth() / onView.getHeight();

                if (imageRatio > canvasRatio) {
                    width = onView.getWidth();
                    height = Math.round(width / imageRatio);
                    x = 0;
                    y = onView.getHeight() / 2 - height / 2;
                } else {
                    height = onView.getHeight();
                    width = Math.round(height * imageRatio);
                    y = 0;
                    x = onView.getWidth() / 2 - width / 2;
                }

                if (mWithBackgroundColor) {
                    g.setColor(mColor);
                    g.fillRect(0, 0, onView.getWidth(), onView.getHeight());
                }

                g.drawImage(mImage, x, y, width, height, mColor, null);

                break;
            default:
                break;
        }
    }

}
