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
    private Stack<BaseController> mControllers = new Stack<>();
    private GameCanvas mCanvas;

    private Application mApplication;

    public GameWindow(Application application) {
        mApplication = application;

        setTitle("Faculty Fighter");
        setSize(640, 500);

        setResizable(false);

        mCanvas = new GameCanvas();

        SplashController splashController = new SplashController(application);
        mCanvas.setController(splashController);
        setJMenuBar(createMenuBar());
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

    public Application getApplication() {
        return mApplication;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Game");
        menuBar.add(menu);

        JMenuItem pauseItem = new JMenuItem("Pause");
        pauseItem.addActionListener(e -> pauseGameIfPossible(pauseItem));
        menu.add(pauseItem);

        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.addActionListener(e -> getApplication().saveGameState());
        menu.add(saveItem);

        JMenuItem loadItem = new JMenuItem("Load");
        loadItem.addActionListener(e -> reloadGame());
        menu.add(loadItem);

        JMenuItem exitWithoutSaving = new JMenuItem("Exit");
        exitWithoutSaving.addActionListener(e -> close());
        menu.add(exitWithoutSaving);

        JMenuItem saveAndExit = new JMenuItem("Save and Exit");
        saveAndExit.addActionListener(e -> {
            getApplication().saveGameState();
            close();
        });
        menu.add(saveAndExit);

        return menuBar;
    }

    public void close() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    public void reloadGame() {
        getApplication().loadGameState();
        mControllers.clear();

        SplashController splashController = new SplashController(getApplication());
        mCanvas.setController(splashController);

        mControllers.push(splashController);
    }

    public void setDebugWindowLocation() {
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] graphicsDevices = graphicsEnvironment.getScreenDevices();
        setLocation(graphicsDevices[0].getDefaultConfiguration().getBounds().x, getY());
    }

    private void pauseGameIfPossible(JMenuItem pauseItem) {
        if (mControllers.peek() instanceof GameController) {
            GameController c = (GameController) mControllers.peek();
            c.getModel().setPaused(!c.getModel().isGamePaused());
            if (c.getModel().isGamePaused()) {
                pauseItem.setText("Resume");
            } else {
                pauseItem.setText("Pause");
            }
        }
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
