package com.jacemcpherson.view;

import com.jacemcpherson.controller.Application;
import com.jacemcpherson.graphics.Background;
import com.jacemcpherson.resources.R;
import com.jacemcpherson.util.ImageUtil;

import java.awt.*;
import java.util.List;

public class SplashView extends BaseView {

    String mDisplayText = "";
    Point mDrawPosition = new Point(10, 10);

    boolean mLoadingImage = false;

    public SplashView(Application application) {
        super(application);
        setBackground(new Background(Color.red));
        mLoadingImage = true;

        ImageUtil.loadImage(R.image.bank, 60, 60, (image, e) -> {
            if (e == null) {
                setBackground(new Background(image, true));
                repaint();
                mLoadingImage = false;
            }
        });

    }

    @Override
    public void paint(Graphics g) {
        if (mLoadingImage) {
            g.drawString("Loading...", getWidth() / 2, getHeight() / 2);
        } else {
            drawBackground(g);
        }
    }

    public void setDisplayText(List<Integer> input) {
        mDisplayText = "";
        if (input.size() > 0) {
            for (Integer i : input.subList(0, input.size() - 1)) {
                mDisplayText += i + ", ";
            }
            mDisplayText += input.get(input.size() - 1);
        }
    }

    public void setDrawPosition(int x, int y) {
        mDrawPosition.x = x;
        mDrawPosition.y = y;
    }

    public void setDrawPosition(Point position) {
        mDrawPosition = position;
    }

    public void changeToRobberBackground() {
        ImageUtil.loadImage(R.image.robber, 60, 60, (image, e) -> {
            if (e == null) {
                setBackground(new Background(image, true));
                repaint();
                mLoadingImage = false;
            }
        });
    }
}
