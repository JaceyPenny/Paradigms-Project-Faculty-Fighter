package com.jacemcpherson.view;

import com.jacemcpherson.controller.Application;
import com.jacemcpherson.graphics.Background;
import com.jacemcpherson.graphics.Draw;
import com.jacemcpherson.graphics.ShapeDrawingOptions;
import com.jacemcpherson.graphics.TextDrawingOptions;
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
                setBG(new Background(image, Background.BackgroundType.IMAGE_CENTER_FIT, Color.black));
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

        Point mousePosition = getMousePosition();
        if (mousePosition != null)
            Draw.drawShape(g, ShapeDrawingOptions.build()
                    .position(mousePosition)
                    .stroke(Color.red)
                    .fill(Color.white)
                    .drawLocation(ShapeDrawingOptions.DrawLocation.CENTERED)
                    .shape(ShapeDrawingOptions.Shape.CIRCLE)
                    .radius(20f)
            );


        drawLoadingText(g);
        Draw.drawText(
                g, "Created by Jace McPherson", this,
                TextDrawingOptions.build()
                        .font(FontUtil.gameFont(18))
                        .color(Color.gray)
                        .horizontalPosition(TextDrawingOptions.HorizontalTextPosition.LEFT)
                        .verticalPosition(TextDrawingOptions.VerticalTextPosition.BOTTOM)
                        .padding(24)
        );

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
                TextDrawingOptions.build()
                        .color(Color.white)
                        .font(FontUtil.gameFont(32))
                        .position(getWidth() - Draw.getTextDrawWidth(g, "Loading...", FontUtil.gameFont(32)) - 32, getHeight() - 32)
        );
    }
}
