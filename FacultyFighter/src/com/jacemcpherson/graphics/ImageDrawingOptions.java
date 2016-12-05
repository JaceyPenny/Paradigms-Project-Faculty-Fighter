package com.jacemcpherson.graphics;

import java.awt.*;

public class ImageDrawingOptions {
    public enum HorizontalImagePosition {
        CENTER, LEFT, RIGHT
    }

    public enum VerticalImagePosition {
        CENTER, TOP, BOTTOM
    }

    protected HorizontalImagePosition horizontalImagePosition = HorizontalImagePosition.CENTER;
    protected VerticalImagePosition veritcalImagePosition = VerticalImagePosition.CENTER;
    protected Color fillColor = new Color(255, 255, 255, 0);
    protected int paddingLeft = 0;
    protected int paddingTop = 0;
    protected int paddingRight = 0;
    protected int paddingBottom = 0;
    protected int shiftLeft = 0;
    protected int shiftUp = 0;
    protected int shiftRight = 0;
    protected int shiftDown = 0;
    protected int width = -1;
    protected int height = -1;
    protected Point position = null;

    public ImageDrawingOptions horizontalPosition(HorizontalImagePosition position) {
        horizontalImagePosition = position;
        return this;
    }
    public ImageDrawingOptions verticalPosition(VerticalImagePosition position) {
        veritcalImagePosition = position;
        return this;
    }
    public ImageDrawingOptions fillColor(Color color) {
        fillColor = color;
        return this;
    }
    public ImageDrawingOptions padding(int l, int t, int r, int b) {
        paddingLeft = l;
        paddingTop = t;
        paddingRight = r;
        paddingBottom = b;
        return this;
    }
    public ImageDrawingOptions padding(int padding) {
        paddingLeft = padding;
        paddingTop = padding;
        paddingRight = padding;
        paddingBottom = padding;
        return this;
    }
    public ImageDrawingOptions paddingLeft(int l) { paddingLeft = l; return this; }
    public ImageDrawingOptions paddingTop(int t) { paddingTop = t; return this; }
    public ImageDrawingOptions paddingRight(int r) { paddingRight = r; return this; }
    public ImageDrawingOptions paddingBottom(int b) { paddingBottom = b; return this; }
    public ImageDrawingOptions shift(int l, int u, int r, int d) {
        shiftLeft = l;
        shiftUp = u;
        shiftRight = r;
        shiftDown = d;
        return this;
    }
    public ImageDrawingOptions shiftLeft(int l) { shiftLeft = l; return this; }
    public ImageDrawingOptions shiftUp(int u) { shiftUp = u; return this; }
    public ImageDrawingOptions shiftRight(int r) { shiftRight = r; return this; }
    public ImageDrawingOptions shiftDown(int d) { shiftDown = d; return this; }
    public ImageDrawingOptions position(int x, int y) {
        position = new Point(x, y);
        return this;
    }
    public ImageDrawingOptions width(int w) {
        width = w;
        return this;
    }
    public ImageDrawingOptions height(int h) {
        height = h;
        return this;
    }
}
