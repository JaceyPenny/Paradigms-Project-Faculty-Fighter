package com.jacemcpherson.view;

import com.jacemcpherson.controller.Application;
import com.jacemcpherson.graphics.Background;
import com.jacemcpherson.graphics.Background.BackgroundType;
import com.jacemcpherson.graphics.Draw;
import com.jacemcpherson.graphics.TextDrawingOptions;
import com.jacemcpherson.graphics.TextDrawingOptions.HorizontalTextPosition;
import com.jacemcpherson.graphics.TextDrawingOptions.VerticalTextPosition;
import com.jacemcpherson.resources.R;
import com.jacemcpherson.util.FontUtil;
import com.jacemcpherson.util.ImageUtil;

import java.awt.*;

public class SplashView extends BaseView {

    private boolean mLoadingBackground = false;

    public SplashView(Application application) {
        super(application);
        setBG(new Background(Color.red));
        mLoadingBackground = true;

        ImageUtil.loadImage(R.image.bg_splash, (image, e) -> {
            if (e == null) {
                setBG(new Background(image, BackgroundType.IMAGE_CENTER_FIT, Color.black));
                mLoadingBackground = false;
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        if (!mLoadingBackground) {
            drawBackground(g);
        }

        Draw.drawText(
                g, "Loading...", this,
                TextDrawingOptions.build()
                        .color(Color.white)
                        .font(FontUtil.gameFont(32))
                        .horizontalPosition(HorizontalTextPosition.RIGHT)
                        .verticalPosition(VerticalTextPosition.BOTTOM)
                        .padding(32)
        );
    }

    public void changeToRobberBackground() {
        mLoadingBackground = true;
        ImageUtil.loadImage(R.image.robber, (image, e) -> {
            if (e == null) {
                //setBG(new Background(image, true, false));
                mLoadingBackground = false;
            }
        });
    }
}
