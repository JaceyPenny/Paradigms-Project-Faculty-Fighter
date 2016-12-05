package com.jacemcpherson.controller;

import com.jacemcpherson.animation.ViewAnimation;
import com.jacemcpherson.exception.LastControllerException;
import com.jacemcpherson.graphics.GameCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;

public class GameWindow extends JFrame implements KeyListener, MouseListener, WindowListener {

    // the controller at the top of the stack will always be the visible controller
    Stack<BaseController> mControllers = new Stack<>();
    GameCanvas mCanvas;

    Dimension[] windowSizes = new Dimension[] {
            new Dimension(640, 480),
            new Dimension(720, 540),
            new Dimension(1280, 720)
    };

    int mWindowSize = 0;

    public GameWindow(Application application) {
        setTitle("Faculty Fighter");
        setSize(windowSizes[mWindowSize]);

        // TODO: Comment this line out
        //setDebugWindowLocation();

        setResizable(false);

        mCanvas = new GameCanvas();

        BaseController splashController = new SplashController(application);
        mCanvas.setController(splashController);
        add(mCanvas, BorderLayout.CENTER);

        mControllers.push(splashController);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mCanvas.setFocusable(true);
        mCanvas.addKeyListener(this);
        mCanvas.addMouseListener(this);

        setVisible(true);

        Thread thread = new Thread(mCanvas);
        thread.start();
    }

    public void setDebugWindowLocation() {
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] graphicsDevices = graphicsEnvironment.getScreenDevices();
        setLocation(graphicsDevices[0].getDefaultConfiguration().getBounds().x, getY());
    }

    public GameCanvas getCanvas() {
        return mCanvas;
    }

    public void moveToController(BaseController controller) {
        mControllers.peek().onPause();
        mControllers.push(controller);
        mCanvas.setController(controller);
        controller.onResume();
    }

    public void moveToController(BaseController controller, ViewAnimation viewAnimation) {
        moveToController(controller);
        mCanvas.animate(viewAnimation);
    }

    public void popController() throws LastControllerException {
        if (mControllers.size() == 1)
            throw new LastControllerException("You cannot pop the only visible controller.");
        else {
            BaseController controller = mControllers.pop();
            controller.onPause();
            mCanvas.setController(mControllers.peek());
            mControllers.peek().onResume();
        }
    }

    public void popController(ViewAnimation animation) throws LastControllerException {
        popController();
        mCanvas.animate(animation);
    }

    public void resizeWindow(int size) {
        mWindowSize = size;
        setSize(windowSizes[mWindowSize]);
        if (mControllers.size() > 0) {
            mCanvas.draw();
            mControllers.peek().onResume();
        }
    }

    public int getWindowSize() {
        return mWindowSize;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        mControllers.peek().keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        mControllers.peek().keyReleased(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mControllers.peek().mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mControllers.peek().mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mControllers.peek().mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mControllers.peek().mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mControllers.peek().mouseExited(e);
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        mCanvas.stopDrawing();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    public BaseController getControllerBefore(BaseController controller) {
        BaseController prev = null;
        for (BaseController c : mControllers) {
            if (c == controller) {
                return prev;
            }
            prev = c;
        }
        return null;
    }
}
