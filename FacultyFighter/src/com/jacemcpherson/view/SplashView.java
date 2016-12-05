package com.jacemcpherson.view;

import com.jacemcpherson.controller.Application;
import com.jacemcpherson.graphics.Background;
import com.jacemcpherson.graphics.Draw;
import com.jacemcpherson.graphics.TextDrawingOptions;
import com.jacemcpherson.model.BaseModel;
import com.jacemcpherson.resources.R;
import com.jacemcpherson.util.FontUtil;
import com.jacemcpherson.util.ImageUtil;

import java.awt.*;

public class SplashView extends BaseView {

    private boolean mLoadingBackground = false;

    public SplashView(Application application, BaseModel model) {
        super(application, model);
        mLoadingBackground = true;

        ImageUtil.loadImage(R.image.bg_splash, (image, e) -> {
            if (e == null) {
                setBackground(new Background(image, Background.BackgroundType.IMAGE_CENTER_FIT, Color.black));
                mLoadingBackground = false;
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (!mLoadingBackground) {
            drawBackground(g);
        }

        drawLoadingText(g);
        drawCreditText(g);
    }

    public void drawLoadingText(Graphics g) {
        int seconds = getFrame() / getCanvas().getFPS();
        String output = "Loading";
        switch (seconds % 4) {
            case 0:
                output += "";
                break;
            case 1:
                output += ".";
                break;
            case 2:
                output += "..";
                break;
            case 3:
                output += "...";
                break;
        }


        Draw.drawText(
                g, output, this,
                new TextDrawingOptions()
                        .color(Color.white)
                        .font(FontUtil.gameFont(32))
                        .position(getWidth() - Draw.getTextDrawWidth(g, "Loading...", FontUtil.gameFont(32)) - 32, getHeight() - 32)
        );
    }
    public void drawCreditText(Graphics g) {
        Draw.drawText(
                g, R.string.credit_text, this,
                new TextDrawingOptions()
                        .font(FontUtil.gameFont(18))
                        .color(Color.gray)
                        .horizontalPosition(TextDrawingOptions.HorizontalTextPosition.LEFT)
                        .verticalPosition(TextDrawingOptions.VerticalTextPosition.BOTTOM)
                        .padding(24)
        );
    }
}
