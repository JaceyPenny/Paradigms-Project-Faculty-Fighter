package com.jacemcpherson.widget;

import com.jacemcpherson.graphics.Background;
import com.jacemcpherson.view.BaseView;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class BaseWidget {

    private int mWidth;
    private int mHeight;
    private int mXPosition;
    private int mYPosition;
    private int mZPosition;

    private BufferedImage mBufferedImage;

    private Background mBackground;

    private BaseView mParentView;

    protected BaseWidget(BaseView parent, int width, int height) {
        this(parent, width, height, 0, 0, 0);
    }

    protected BaseWidget(BaseView parent, int width, int height, int xPosition, int yPosition) {
        this(parent, width, height, xPosition, yPosition, 0);
    }

    protected BaseWidget(BaseView parent, int width, int height, int zPosition) {
        this(parent, width, height, 0, 0, zPosition);
    }

    protected BaseWidget(BaseView parent, int width, int height, int xPosition, int yPosition, int zPosition) {
        mParentView = parent;
        mWidth = width;
        mHeight = height;
        mXPosition = xPosition;
        mYPosition = yPosition;
        mZPosition = zPosition;
        mBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    public void draw(Graphics g) {

    }

    public int getWidth() {
        return mWidth;
    }

    private void setWidth(int width) {
        mWidth = width;
    }

    public int getHeight() {
        return mHeight;
    }

    private void setHeight(int height) {
        mHeight = height;
    }

    public Point getPosition() {
        return new Point(mXPosition, mYPosition);
    }

    public void setPosition(int x, int y) {
        mXPosition = x;
        mYPosition = y;
    }

    public int getZPosition() {
        return mZPosition;
    }

    public void setZPosition(int z) {
        mZPosition = z;
    }

    public void setBackground(Background b) {
        mBackground = b;
    }



    protected BufferedImage getBufferedImage() {
        return mBufferedImage;
    }

}
