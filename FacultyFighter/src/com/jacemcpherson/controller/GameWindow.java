package com.jacemcpherson.controller;

import com.jacemcpherson.graphics.GameCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;

public class GameWindow extends JFrame implements KeyListener, MouseListener, WindowListener {

    Stack<BaseController> mControllers = new Stack<>();
    GameCanvas mCanvas;

    public GameWindow(Application application) {
        setTitle("Terraria Paradigms");
        setSize(720, 540);

        // TODO: Comment this line out
        setDebugWindowLocation();

        setResizable(false);

        mCanvas = new GameCanvas(60); // kind of a noob fps, but damn this computer gets hot at 60, v bad optimization on my part

        BaseController splashController = new SplashController(application);
        mCanvas.setView(splashController.getView());
        add(mCanvas, BorderLayout.CENTER);

        mControllers.push(splashController);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(this);
        addMouseListener(this);

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

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        mControllers.peek().keyPressed(e);
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        mControllers.peek().keyReleased(e);
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mControllers.peek().mouseClicked(e);
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mControllers.peek().mousePressed(e);
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mControllers.peek().mouseReleased(e);
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mControllers.peek().mouseEntered(e);
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mControllers.peek().mouseExited(e);
        repaint();
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
}
