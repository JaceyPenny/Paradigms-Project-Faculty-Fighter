package com.jacemcpherson.widget;

import com.jacemcpherson.graphics.Background;
import com.jacemcpherson.graphics.Draw;
import com.jacemcpherson.graphics.Sizeable;
import com.jacemcpherson.util.ImageUtil;
import com.jacemcpherson.view.BaseView;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public abstract class BaseWidget implements MouseMotionListener, MouseListener, Sizeable {
    public static final OnClickListener EMPTY_LISTENER = widget -> {
    };

    public enum MouseState {
        OPEN, HOVERED, PRESSED
    }

    public enum HorizontalGravity {
        LEFT, CENTER, RIGHT
    }

    public enum VerticalGravity {
        TOP, CENTER, BOTTOM
    }

    private MouseState mMouseState = MouseState.OPEN;

    private int mXPosition;
    private int mYPosition;
    private int mZPosition;

    private VerticalGravity mVerticalGravity;
    private HorizontalGravity mHorizontalGravity;

    private int mPaddingLeft;
    private int mPaddingTop;
    private int mPaddingRight;
    private int mPaddingBottom;

    private BufferedImage mBufferedImage;

    private Background mBackground;

    private BaseView mParentView;

    protected OnClickListener mOnClickListener = EMPTY_LISTENER;

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
        mXPosition = xPosition;
        mYPosition = yPosition;
        mZPosition = zPosition;
        mBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    public void drawBackground() {
        if (mBackground != null) {
            mBackground.draw(mBufferedImage);
        }
    }

    public void drawWidget() {
        ImageUtil.clearToTransparent(mBufferedImage);
        drawBackground();
    }

    public void paint(Graphics g) {
        if (mBufferedImage != null) {
            Point position = calculatePosition();
            Draw.drawImage(g, mBufferedImage, position.x, position.y);
        }
    }

    public int getWidth() {
        return mBufferedImage.getWidth();
    }

    public void setWidth(int width) {
        mBufferedImage = new BufferedImage(width, mBufferedImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        drawWidget();
    }

    public int getHeight() {
        return mBufferedImage.getHeight();
    }

    public void setHeight(int height) {
        mBufferedImage = new BufferedImage(mBufferedImage.getWidth(), height, BufferedImage.TYPE_INT_ARGB);
        drawWidget();
    }

    public Point getPosition() {
        return calculatePosition();
    }

    public void setPosition(int x, int y) {
        mXPosition = x;
        mYPosition = y;
    }

    private Point calculatePosition() {
        if (mXPosition == -1) {
            int x = 0, y = 0;
            if (mVerticalGravity != null) {
                switch (mVerticalGravity) {
                    case TOP:
                        y = mPaddingTop;
                        break;
                    case CENTER:
                        y = mParentView.getHeight() / 2 - getHeight() / 2;
                        break;
                    case BOTTOM:
                        y = mParentView.getHeight() - getHeight() - mPaddingBottom;
                }
            }

            if (mHorizontalGravity != null) {
                switch (mHorizontalGravity) {
                    case LEFT:
                        x = mPaddingLeft;
                        break;
                    case CENTER:
                        x = mParentView.getWidth() / 2 - getWidth() / 2;
                        break;
                    case RIGHT:
                        x = mParentView.getWidth() - getWidth() - mPaddingRight;
                        break;
                }
            }

            return new Point(x, y);
        } else {
            return new Point(mXPosition, mYPosition);
        }
    }

    public int getZPosition() {
        return mZPosition;
    }

    public void setZPosition(int z) {
        mZPosition = z;
    }

    public void setVerticalGravity(VerticalGravity gravity) {
        mXPosition = -1;
        mYPosition = -1;
        mVerticalGravity = gravity;
    }

    public void setHorizontalGravity(HorizontalGravity gravity) {
        mXPosition = -1;
        mYPosition = -1;
        mHorizontalGravity = gravity;
    }

    public void setPadding(int left, int top, int right, int bottom) {
        mPaddingLeft = left;
        mPaddingTop = top;
        mPaddingRight = right;
        mPaddingBottom = bottom;
    }

    public MouseState getMouseState() {
        return mMouseState;
    }

    public void updateBackground() {
    }

    public void setBackground(Background b) {
        mBackground = b;
        drawWidget();
    }

    protected BufferedImage getBufferedImage() {
        return mBufferedImage;
    }

    public Rectangle getBounds() {
        Point position = calculatePosition();
        return new Rectangle(position.x, position.y, mBufferedImage.getWidth(), mBufferedImage.getHeight());
    }

    public void setOnClickListener(OnClickListener listener) {
        mOnClickListener = listener;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        boolean inButton = getBounds().contains(e.getPoint());

        MouseState oldState = mMouseState;
        if (oldState == MouseState.PRESSED && !inButton) {
            mMouseState = MouseState.OPEN;
        }
        if (oldState != mMouseState) {
            updateBackground();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        boolean inButton = getBounds().contains(e.getPoint());

        MouseState oldState = mMouseState;
        mMouseState = (inButton) ? MouseState.HOVERED : MouseState.OPEN;
        if (oldState != mMouseState) {
            updateBackground();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        boolean inButton = getBounds().contains(e.getPoint());

        MouseState oldState = mMouseState;
        mMouseState = (inButton) ? MouseState.PRESSED : MouseState.OPEN;

        if (oldState != mMouseState) {
            updateBackground();
        }
    }

    public void mouseReleased(MouseEvent e) {

        boolean inButton = getBounds().contains(e.getPoint());

        MouseState oldState = mMouseState;
        mMouseState = (inButton) ? MouseState.HOVERED : MouseState.OPEN;
        if (oldState != mMouseState) {
            updateBackground();
        }

        if (oldState == MouseState.PRESSED && inButton) {
            mOnClickListener.onClick(this);
        }
    }
}
