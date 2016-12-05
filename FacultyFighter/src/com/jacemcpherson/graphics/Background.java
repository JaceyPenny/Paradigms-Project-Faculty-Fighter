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

    BufferedImage mBuffer = null;

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

    public void draw(Graphics g, int viewWidth, int viewHeight) {
        int width = 0;
        int height = 0;
        int x = 0;
        int y = 0;

        switch (mType) {
            case COLOR:
                g.setColor(mColor);
                g.fillRect(0, 0, viewWidth, viewHeight);
                break;
            case IMAGE:
                if (mWithBackgroundColor) {
                    g.setColor(mColor);
                    g.fillRect(0, 0, viewWidth, viewHeight);
                }
                g.drawImage(mImage, 0, 0, mImage.getWidth(), mImage.getHeight(), new Color(0x00000000), null);
                break;
            case IMAGE_SCALED:
                if (mBuffer == null) {
                    mBuffer = new BufferedImage(viewWidth, viewHeight, BufferedImage.TYPE_INT_ARGB);

                    width = viewWidth;
                    height = Math.round(mImage.getHeight() * ((float) width / mImage.getWidth()));

                    if (height < viewHeight) {
                        height = viewHeight;
                        width = Math.round(mImage.getHeight() * ((float) height / mImage.getHeight()));
                    }

                    mBuffer.getGraphics().drawImage(mImage, 0, 0, width, height, new Color(0x00FFFFFF), null);
                }
                g.drawImage(mBuffer, 0, 0, viewWidth, viewHeight, new Color(255, 255, 255, 0), null);
                break;
            case IMAGE_TESS:
                int compWidth = viewWidth;
                int compHeight = viewHeight;
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
                float canvasRatio = (float) viewWidth / viewHeight;

                if (imageRatio > canvasRatio) {
                    width = viewWidth;
                    height = Math.round(width / imageRatio);
                    x = 0;
                    y = viewHeight / 2 - height / 2;
                } else {
                    height = viewHeight;
                    width = Math.round(height * imageRatio);
                    y = 0;
                    x = viewWidth / 2 - width / 2;
                }

                if (mWithBackgroundColor) {
                    g.setColor(mColor);
                    g.fillRect(0, 0, viewWidth, viewHeight);
                }

                g.drawImage(mImage, x, y, width, height, mColor, null);

                break;
            default:
                break;
        }
    }

    public void draw(BufferedImage image) {
        draw(image.getGraphics(), image.getWidth(), image.getHeight());
    }

    public void draw(Graphics g, BaseView onView) {
        draw(g, onView.getWidth(), onView.getHeight());
    }

}
