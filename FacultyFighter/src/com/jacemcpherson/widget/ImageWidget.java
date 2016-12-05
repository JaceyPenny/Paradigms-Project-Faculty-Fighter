package com.jacemcpherson.widget;

import com.jacemcpherson.graphics.Background;
import com.jacemcpherson.graphics.Draw;
import com.jacemcpherson.view.BaseView;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class ImageWidget extends BaseWidget {

    BufferedImage mImage;

    private Background OPEN = new Background(new Color(255, 255, 255, 0));
    private Background HOVERED = new Background(new Color(120, 120, 120, 255));
    private Background PRESSED = new Background(new Color(200, 200, 200, 255));

    public ImageWidget(BaseView parentView, BufferedImage image) {
        super(parentView, image.getWidth(), image.getHeight());
        mImage = image;
        setBackground(OPEN);
        setClickable(false);
    }

    @Override
    public void drawWidget() {
        super.drawWidget();

        BufferedImage canvas = getBufferedImage();

        drawBackground();

        Draw.drawImage(canvas.getGraphics(), mImage, 0, 0, canvas.getWidth(), canvas.getHeight());
    }

    @Override
    public void updateBackground() {
        if (isClickable()) {
            switch (getMouseState()) {
                case OPEN:
                    setBackground(OPEN);
                    break;
                case HOVERED:
                    setBackground(HOVERED);
                    break;
                case PRESSED:
                    setBackground(PRESSED);
                    break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isClickable()) {
            super.mouseReleased(e);
        }
    }
}
