package com.jacemcpherson.view;

import com.jacemcpherson.controller.Application;
import com.jacemcpherson.graphics.Background;
import com.jacemcpherson.resources.R;
import com.jacemcpherson.util.FontUtil;
import com.jacemcpherson.util.ImageUtil;

import java.awt.*;

public class SplashView extends BaseView {

    String mDisplayText = "";

    boolean mLoadingBackground = false;

    public SplashView(Application application) {
        super(application);
        setBG(new Background(Color.red));
        mLoadingBackground = true;

        ImageUtil.loadImage(R.image.bg_splash, (image, e) -> {
            if (e == null) {
                setBG(new Background(image, false, true));
                mLoadingBackground = false;
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        if (!mLoadingBackground) {
            drawBackground(g);
        }
        g.setFont(FontUtil.gameFont(32f));
        g.setColor(Color.black);
        int textLength = FontUtil.getDrawWidth(g, "Loading...");
        g.drawString("Loading...", getWidth() / 2 - textLength / 2, getHeight() / 2);
    }

    public void changeToRobberBackground() {
        mLoadingBackground = true;
        ImageUtil.loadImage(R.image.robber, (image, e) -> {
            if (e == null) {
                setBG(new Background(image, true, false));
                mLoadingBackground = false;
            }
        });
    }
}
