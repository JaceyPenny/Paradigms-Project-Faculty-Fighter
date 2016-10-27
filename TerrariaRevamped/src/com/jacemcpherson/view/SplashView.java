package com.jacemcpherson.view;

import com.jacemcpherson.controller.Application;
import com.jacemcpherson.util.Console;

import java.awt.*;
import java.util.List;

public class SplashView extends BaseView {

    String mDisplayText = "";
    Point mDrawPosition = new Point(10, 10);

    public SplashView(Application application) {
        super(application);
    }

    @Override
    public void paint(Graphics g) {
        Console.i("Drawing at " + mDrawPosition);
        g.drawString(mDisplayText, mDrawPosition.x, mDrawPosition.y);
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
}
