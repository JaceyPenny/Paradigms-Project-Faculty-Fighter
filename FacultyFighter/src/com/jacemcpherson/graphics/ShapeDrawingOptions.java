package com.jacemcpherson.graphics;

import java.awt.*;
import java.util.List;

import static com.jacemcpherson.graphics.ShapeDrawingOptions.DrawLocation.CENTERED;

public class ShapeDrawingOptions {
    public enum Shape {
        CIRCLE, RECTANGLE, POLYGON
    }

    public enum DrawLocation {
        CENTERED, TOP_RIGHT, TOP_LEFT, BOTTOM_RIGHT, BOTTOM_LEFT
    }

    protected Shape shape = Shape.CIRCLE;
    protected int[] xPoints, yPoints;
    protected Color fillColor = Color.black;
    protected Color strokeColor = Color.black;
    protected boolean filled = false;
    protected boolean stroked = false;
    protected float width;
    protected float height;
    protected Point position = null;
    protected DrawLocation drawLocation = CENTERED;

    public static ShapeDrawingOptions build() {
        return new ShapeDrawingOptions();
    }

    public ShapeDrawingOptions shape(Shape shape, Object... args) {
        this.shape = shape;
        if (shape == Shape.POLYGON) {
            if (args.length == 1) {
                List<Point> points = (List<Point>) args[0];
                xPoints = new int[points.size()]; yPoints = new int[points.size()];
                for (int i = 0; i < points.size(); i++) {
                    xPoints[i] = points.get(i).x;
                    yPoints[i] = points.get(i).y;
                }
            } else if (args.length == 2) {
                xPoints = (int[]) args[0];
                yPoints = (int[]) args[1];
            }
        }
        return this;
    }
    public ShapeDrawingOptions fill(Color color) {
        this.fillColor = color;
        this.filled = true;
        return this;
    }
    public ShapeDrawingOptions stroke(Color color) {
        this.strokeColor = color;
        this.stroked = true;
        return this;
    }
    public ShapeDrawingOptions radius(float radius) { this.width = this.height = radius; return this; }
    public ShapeDrawingOptions width(float width) { this.width = width; return this; }
    public ShapeDrawingOptions height(float height) { this.height = height; return this; }
    public ShapeDrawingOptions position(Point p) {
        position = p;
        return this;
    }
    public ShapeDrawingOptions position(int x, int y) {
        position = new Point(x, y);
        return this;
    }
    public ShapeDrawingOptions drawLocation(DrawLocation drawLocation) {
        this.drawLocation = drawLocation;
        return this;
    }
}
