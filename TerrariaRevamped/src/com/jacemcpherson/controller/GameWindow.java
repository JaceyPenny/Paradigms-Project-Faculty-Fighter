package com.jacemcpherson.controller;

import javax.swing.*;
import java.awt.event.*;
import java.util.Stack;

public class GameWindow extends JFrame implements KeyListener, MouseListener {

    Stack<BaseController> mControllers = new Stack<>();

    public GameWindow(Application application) {
        setTitle("Terraria Paradigms");
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);

        BaseController splashController = new SplashController(application);
        mControllers.push(splashController);
        getContentPane().add(splashController.getView());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(this);
        addMouseListener(this);
    }

    public void repaint() {
        mControllers.peek().getView().repaint();
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
}
