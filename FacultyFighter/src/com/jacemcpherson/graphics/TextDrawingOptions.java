package com.jacemcpherson.graphics;

import java.awt.*;

public class TextDrawingOptions {
    public enum HorizontalTextPosition {
        CENTER, LEFT, RIGHT
    }

    public enum VerticalTextPosition {
        CENTER, TOP, BOTTOM
    }

    protected HorizontalTextPosition horizontalTextPosition = HorizontalTextPosition.CENTER;
    protected VerticalTextPosition verticalTextPosition = VerticalTextPosition.CENTER;
    protected Font font = new Font("TimesRoman", Font.PLAIN, 18);
    protected Color color = Color.black;
    protected int paddingLeft = 0;
    protected int paddingTop = 0;
    protected int paddingRight = 0;
    protected int paddingBottom = 0;
    protected Point position = null;

    public TextDrawingOptions horizontalPosition(HorizontalTextPosition position) {
        this.horizontalTextPosition = position;
        return this;
    }
    public TextDrawingOptions verticalPosition(VerticalTextPosition position) {
        this.verticalTextPosition = position;
        return this;
    }
    public TextDrawingOptions font(Font font) { this.font = font; return this; }
    public TextDrawingOptions color(Color color) { this.color = color; return this; }
    public TextDrawingOptions padding(int l, int t, int r, int b) {
        paddingLeft = l;
        paddingTop = t;
        paddingRight = r;
        paddingBottom = b;
        return this;
    }
    public TextDrawingOptions padding(int padding) {
        paddingLeft = padding;
        paddingRight = padding;
        paddingTop = padding;
        paddingBottom = padding;
        return this;
    }
    public TextDrawingOptions paddingLeft(int l) { paddingLeft = l; return this; }
    public TextDrawingOptions paddingTop(int t) { paddingTop = t; return this; }
    public TextDrawingOptions paddingRight(int r) { paddingRight = r; return this; }
    public TextDrawingOptions paddingBottom(int b) { paddingBottom = b; return this; }
    public TextDrawingOptions position(Point p) {
        position = p;
        return this;
    }
    public TextDrawingOptions position(int x, int y) {
        position = new Point(x, y);
        return this;
    }

}
