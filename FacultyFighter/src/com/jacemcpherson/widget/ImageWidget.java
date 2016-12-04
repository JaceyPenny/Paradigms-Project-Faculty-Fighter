package com.jacemcpherson.widget;

import com.jacemcpherson.graphics.Background;
import com.jacemcpherson.view.BaseView;

import java.awt.image.BufferedImage;

public class ImageWidget extends BaseWidget {

    public ImageWidget(BaseView parentView, BufferedImage image) {
        super(parentView, image.getWidth(), image.getHeight());
        setBackground(new Background(image, Background.BackgroundType.IMAGE));
    }
}
