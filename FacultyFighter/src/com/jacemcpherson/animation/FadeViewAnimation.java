package com.jacemcpherson.animation;

import com.jacemcpherson.util.MathUtil;
import com.jacemcpherson.view.BaseView;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FadeViewAnimation extends ViewAnimation {

    public static class Builder {
        int durationFrames = ViewAnimation.GLOBAL_FPS;
        BaseView outView, inView;

        public Builder() {
        }

        public Builder durationMillis(int millis) {
            durationFrames = MathUtil.millisToFrames(millis);
            return this;
        }

        public Builder durationFrames(int frames) {
            durationFrames = frames;
            return this;
        }

        public Builder durationSeconds(int seconds) {
            durationFrames = MathUtil.secondsToFrames(seconds);
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

        public FadeViewAnimation build() throws InvalidAnimationException {
            if (outView == null || inView == null) {
                throw new InvalidAnimationException("You must set two non-null views for in and out animations.");
            }
            FadeViewAnimation animation = new FadeViewAnimation(durationFrames);
            animation.setViews(outView, inView);
            return animation;
        }
    }

    public FadeViewAnimation(int duration) {
        super(duration);
    }

    @Override
    public void drawAnimation(Graphics g, Dimension canvasSize) {
        super.drawAnimation(g, canvasSize);
        BufferedImage outImage = new BufferedImage((int) canvasSize.getWidth(), (int) canvasSize.getHeight(), BufferedImage.TYPE_INT_ARGB);
        mOutView.paint(outImage.getGraphics());

        BufferedImage inImage = new BufferedImage((int) canvasSize.getWidth(), (int) canvasSize.getHeight(), BufferedImage.TYPE_INT_ARGB);
        mInView.paint(inImage.getGraphics());

        if (g instanceof Graphics2D) {
            Graphics2D graphics2D = (Graphics2D) g;
            graphics2D.drawImage(outImage, 0, 0, outImage.getWidth(), outImage.getHeight(), new Color(0x00000000), null);
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, getOpacity()));
            graphics2D.drawImage(inImage, 0, 0, inImage.getWidth(), inImage.getHeight(), new Color(0x00000000), null);
        } else {
            g.drawImage(inImage, 0, 0, inImage.getWidth(), inImage.getHeight(), new Color(0x00000000), null);
        }


    }

    public float getOpacity() {
        return (float) mCurrentFrame / mDurationFrames;
    }
}
