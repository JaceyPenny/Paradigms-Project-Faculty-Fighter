package com.jacemcpherson.animation;

import com.jacemcpherson.util.MathUtil;
import com.jacemcpherson.view.BaseView;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import static com.jacemcpherson.animation.TranslateViewAnimation.Direction.STATIONARY;
import static com.jacemcpherson.animation.TranslateViewAnimation.Interpolator.LINEAR;

public class TranslateViewAnimation extends ViewAnimation {
    Direction mOutDirection;
    Direction mInDirection;

    Interpolator mOutInterpolator;
    Interpolator mInInterpolator;

    public enum Direction {
        STATIONARY, UP, DOWN, LEFT, RIGHT
    }

    public enum Interpolator {
        LINEAR, ACCELERATE_DECELERATE, ACCELERATE
    }

    public static class Builder {
        int durationFrames = ViewAnimation.GLOBAL_FPS;
        Direction outDirection = STATIONARY, inDirection = STATIONARY;
        Interpolator outInterpolator = LINEAR, inInterpolator = LINEAR;
        BaseView outView, inView;

        public Builder() {

        }

        public Builder durationMillis(int durationMs) {
            this.durationFrames = MathUtil.millisToFrames(durationMs);
            return this;
        }

        public Builder durationFrames(int durationFrames) {
            this.durationFrames = durationFrames;
            return this;
        }

        public Builder durationSeconds(int durationSeconds) {
            this.durationFrames = MathUtil.secondsToFrames(durationSeconds);
            return this;
        }

        public Builder direction(Direction direction) {
            outDirection = direction;
            inDirection = direction;
            return this;
        }

        public Builder outDirection(Direction direction) {
            outDirection = direction;
            return this;
        }

        public Builder inDirection(Direction direction) {
            inDirection = direction;
            return this;
        }

        public Builder interpolator(Interpolator interpolator) {
            outInterpolator = interpolator;
            inInterpolator = interpolator;
            return this;
        }

        public Builder outInterpolator(Interpolator interpolator) {
            outInterpolator = interpolator;
            return this;
        }

        public Builder inInterpolator(Interpolator interpolator) {
            inInterpolator = interpolator;
            return this;
        }

        public Builder outView(BaseView view) {
            outView = view;
            return this;
        }

        public Builder inView(BaseView view) {
            inView = view;
            return this;
        }

        public TranslateViewAnimation build() throws InvalidAnimationException {
            if (outView == null || inView == null) {
                throw new InvalidAnimationException("You must set two non-null views for in and out animations.");
            }
            TranslateViewAnimation animation = new TranslateViewAnimation(durationFrames, outDirection, inDirection, outInterpolator, inInterpolator);
            animation.setViews(outView, inView);
            return animation;
        }
    }

    public TranslateViewAnimation(int duration, Direction out, Direction in, Interpolator outInterpolator, Interpolator inInterpolator) {
        super(duration);
        mOutDirection = out;
        mInDirection = in;
        mOutInterpolator = outInterpolator;
        mInInterpolator = inInterpolator;
    }

    @Override
    public void drawAnimation(Graphics g, Dimension canvasSize) {
        super.drawAnimation(g, canvasSize);

        Point2D outPosition = getOutPosition(canvasSize);
        Point2D inPosition = getInPosition(canvasSize);

        BufferedImage outImage = new BufferedImage((int)canvasSize.getWidth(), (int)canvasSize.getHeight(), BufferedImage.TYPE_INT_RGB);
        mOutView.paint(outImage.getGraphics());

        BufferedImage inImage = new BufferedImage((int) canvasSize.getWidth(), (int)canvasSize.getHeight(), BufferedImage.TYPE_INT_RGB);
        mInView.paint(inImage.getGraphics());

        g.drawImage(outImage, (int)outPosition.getX(), (int)outPosition.getY(), outImage.getWidth(), outImage.getHeight(), new Color(0x00000000), null);
        g.drawImage(inImage, (int)inPosition.getX(), (int)inPosition.getY(), inImage.getWidth(), inImage.getHeight(), new Color(0x00000000), null);
    }

    public Point2D getOutPosition(Dimension size) {
        Point2D point = new Point2D.Double();

        double xPosition, yPosition;

        switch (mOutDirection) {
            case STATIONARY:
                point.setLocation(0.0, 0.0);
                return point;
            case LEFT:
                xPosition = getInterpolationPosition(0.0, -1 * size.getWidth(), mOutInterpolator);
                point.setLocation(xPosition, 0.0);
                return point;
            case RIGHT:
                xPosition = getInterpolationPosition(0.0, size.getWidth(), mOutInterpolator);
                point.setLocation(xPosition, 0.0);
                return point;
            case UP:
                yPosition = getInterpolationPosition(0.0, -1 * size.getHeight(), mOutInterpolator);
                point.setLocation(0.0, yPosition);
                return point;
            case DOWN:
                yPosition = getInterpolationPosition(0.0, size.getHeight(), mOutInterpolator);
                point.setLocation(0.0, yPosition);
                return point;
            default:
                return point;
        }
    }

    public Point2D getInPosition(Dimension size) {
        Point2D point = new Point2D.Double();

        double xPosition, yPosition;

        switch (mInDirection) {
            case STATIONARY:
                point.setLocation(0.0, 0.0);
                return point;
            case LEFT:
                xPosition = getInterpolationPosition(-1 * size.getWidth(), 0.0, mOutInterpolator);
                point.setLocation(xPosition, 0.0);
                return point;
            case RIGHT:
                xPosition = getInterpolationPosition(size.getWidth(), 0.0, mOutInterpolator);
                point.setLocation(xPosition, 0.0);
                return point;
            case UP:
                yPosition = getInterpolationPosition(-1 * size.getHeight(), 0.0, mOutInterpolator);
                point.setLocation(0.0, yPosition);
                return point;
            case DOWN:
                yPosition = getInterpolationPosition(size.getHeight(), 0.0, mOutInterpolator);
                point.setLocation(0.0, yPosition);
                return point;
            default:
                return point;
        }
    }

    private double getInterpolationPosition(double start, double end, Interpolator interpolator) {
        double interpolation = getInterpolation(interpolator);
        return MathUtil.map(interpolation, 0.0, 1.0, start, end);
    }

    private double getInterpolation(Interpolator interpolator) {
        switch (interpolator) {
            case LINEAR:
                return (double) mCurrentFrame / (double) mDurationFrames;
            case ACCELERATE:
                return Math.pow((double) mCurrentFrame / (double) mDurationFrames, 2);
            case ACCELERATE_DECELERATE:
                double t = mCurrentFrame;
                double d = mDurationFrames;
                t /= d/2;
                if (t < 1) return 1/2*t*t*t;
                t -= 2;
                return 1/2*(t*t*t + 2);
            default:
                return 0.0;
        }
    }

}
