package com.gurpy.games.gui;

import com.gurpy.games.pojos.action.gui.DrawAction;
import com.gurpy.games.pojos.component.RenderingComponent;
import com.gurpy.games.pojos.control.Camera;
import com.gurpy.games.pojos.control.UIMouseListener;
import com.gurpy.games.pojos.entities.*;
import com.gurpy.games.pojos.entities.Menu;
import com.gurpy.games.pojos.entities.MenuItem;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.util.concurrent.CopyOnWriteArrayList;

public class GurpusUI extends JPanel {
    //Allow for easy scaling of UIElements.
    private final int MAX_FRAME_RATE = 120;
    private final int SCALING = 1;
    public final int MIN_X = -7680;
    public final int MIN_Y = -4320;
    public final int MAX_X = 7680;
    public final int MAX_Y = 4320;
    private final long NANO_IN_SEC = 1000000000;
    private long start = 0;
    private long lastFrameTime = 0;
    private int numFramesInSecond = 0;
    private volatile int currentWidth = 0;
    private volatile int currentHeight = 0;
    private volatile int fps = 0;
    private volatile Camera camera;
    private volatile UIElement mainPlayer;
    private CopyOnWriteArrayList<UIElement> guiElements = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<Integer> keyCodes = new CopyOnWriteArrayList<>();
    private UIMouseListener uiMouseListener;
    private RenderingComponent renderingComponent;

    /**
     * Custom constructor for OSPanel Object.
     *
     * Creates and adds all UIElements to an ArrayList.
     * Sets the children for each button.
     *
     */
    public GurpusUI() {
        //Self explanatory
        this.setBackground(Color.BLACK);
        this.setSize(new Dimension(1920, 1080));
        this.setPreferredSize(new Dimension(1920, 1080));
        this.setDoubleBuffered(true);
        currentWidth = getPreferredSize().getSize().width;
        currentHeight = getPreferredSize().getSize().height;

        //Add custom MouseListeners.
        uiMouseListener = new UIMouseListener();
        addMouseListener(uiMouseListener);
        addMouseMotionListener(uiMouseListener);
        addMouseWheelListener(uiMouseListener);
        //KeyListeners only work on focused windows.
        this.setFocusable(true);
        this.requestFocusInWindow();

        //Anonymous class for KeyListener.
        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                synchronized (GurpusUI.class) {
                    keyCodes.addIfAbsent(e.getKeyCode());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                synchronized (GurpusUI.class) {
                    keyCodes.remove(keyCodes.indexOf(e.getKeyCode()));
                }
            }

            @Override
            public void keyTyped (KeyEvent e) {
                //Must be implemented to satisfy KeyListener.
            }
        });

        camera = new Camera(0, getWidth(), 0, getHeight(), MIN_X, MAX_X - MIN_X, MIN_Y, MAX_Y - MIN_Y);
        renderingComponent = new RenderingComponent();
        lastFrameTime = System.nanoTime();
        addMenu(true);

    }

    public void resetGame() {

        final double MAIN_PLAYER_START_WIDTH = 100;
        final double MAIN_PLAYER_START_HEIGHT = 100;

        guiElements.clear();
        TextElement fpsCounter = new TextElement(
                new Point2D.Double(10, 25),
                Color.BLACK,
                Color.RED,
                "FPS: 0",
                10.0,
                true);
        fpsCounter.setDisplay(true);
        addGuiElement(fpsCounter);
        addGuiElement(new TextElement(
                new Point2D.Double(getWidth() * .9, 25),
                Color.RED.darker(),
                Color.WHITE,
                "Kill Count: 0",
                20.0,
                true));
        addGuiElement(new TextElement(
                new Point2D.Double(getWidth() * .9, 50),
                Color.MAGENTA.darker(),
                Color.WHITE,
                "Score: 0",
                20.0,
                true));

        addMenu(false);
        BBoxPlayer player = new BBoxPlayer(
                new Point2D.Double(getWidth() / 2 - MAIN_PLAYER_START_WIDTH / 2, getHeight() / 2 - MAIN_PLAYER_START_HEIGHT / 2),
                new Dimension((int)MAIN_PLAYER_START_WIDTH * SCALING, (int)MAIN_PLAYER_START_HEIGHT * SCALING),
                Color.BLACK,
                Color.RED,
                10.0,
                5);
        player.setLaserColor(Color.GREEN);
        player.setLaserBorder(Color.YELLOW.darker().darker());
        player.setNumBullets(3);
        player.setBulletHeight(50);
        player.setBulletWidth(20);
        player.setShotSpeed(50);
        player.setFireRate(25);
        player.setRange(2);
        player.setDoubleShot(true);
        player.setHspeed(5);
        player.setVspeed(5);
        player.setControllable(true);
        player.setFocused(true);
        addGuiElement(player);
        camera = new Camera(0, getWidth(), 0, getHeight(), MIN_X, MAX_X - MIN_X, MIN_Y, MAX_Y - MIN_Y);

    }

    private void addMenu(boolean display) {
        CopyOnWriteArrayList<MenuItem> menuItems = new CopyOnWriteArrayList<>();
        menuItems.add(new MenuItem(
                new Point2D.Double(getWidth() / 2 - 200 * SCALING, getHeight() / 4),
                new Dimension(400 * SCALING, 50 * SCALING),
                Color.WHITE,
                Color.RED,
                "Play Game",
                5.0));
        menuItems.add(new MenuItem(
                new Point2D.Double(getWidth() / 2 - 200 * SCALING, getHeight() / 4 + 100),
                new Dimension(400 * SCALING, 50 * SCALING),
                Color.WHITE,
                Color.RED,
                "Options",
                5.0));
        menuItems.add(new MenuItem(
                new Point2D.Double(getWidth() / 2 - 200 * SCALING, getHeight() / 4 + 200),
                new Dimension(400 * SCALING, 50 * SCALING),
                Color.WHITE,
                Color.RED,
                "Controls",
                5.0));
        menuItems.add(new MenuItem(
                new Point2D.Double(getWidth() / 2 - 200 * SCALING, getHeight() / 4 + 300),
                new Dimension(400 * SCALING, 50 * SCALING),
                Color.WHITE,
                Color.RED,
                "Exit Game",
                5.0));
        TextElement menuTitle = new TextElement(
                new Point2D.Double(getWidth() / 3, getHeight() / 8),
                Color.WHITE,
                Color.BLACK,
                "Gurpus Maximus",
                24,
                true);
        addGuiElement(new Menu(menuTitle, menuItems, display));
    }

    @Override
    protected void paintComponent(Graphics g) {
        //Calls System Graphics Component.
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints renderingHints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        renderingHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(renderingHints);
        super.paintComponent(g2d);
        //Iterate over the GUI in order to repaint changes.
        //Iterate through each UIElement.
        for (UIElement uiElement : guiElements) {
            if (uiElement.isDisplay())
                renderingComponent.performAction(new DrawAction(uiElement, g2d));
        }

        //Method to update current FPS.
        checkFPS();
        capFrameRate();
        Toolkit.getDefaultToolkit().sync();
        //Effectively recalls paintComponent(g);
        repaint();
    }

    private void capFrameRate() {
        long diff, wait;
        wait = NANO_IN_SEC / MAX_FRAME_RATE;
        diff = System.nanoTime() - start;
        while (diff < wait) {
            diff = System.nanoTime() - start;
        }
        start = System.nanoTime();
    }

    public CopyOnWriteArrayList<UIElement> getGuiElements() {
        return guiElements;
    }

    private void addGuiElement(UIElement uiElement) {
        guiElements.add(uiElement);
    }

    public CopyOnWriteArrayList<Integer> getKeyCodes(){
        return keyCodes;
    }

    public double getMouseX(){
        return uiMouseListener.getxPos();
    }

    public double getMouseY(){
        return uiMouseListener.getyPos();
    }

    public double getMouseClickX(){
        return uiMouseListener.getxPress();
    }

    public double getMouseClickY(){
        return uiMouseListener.getyPress();
    }

    public int getCurrentWidth() {
        return currentWidth;
    }

    public void setCurrentWidth(int currentWidth) {
        this.currentWidth = currentWidth;
    }

    public int getCurrentHeight() {
        return currentHeight;
    }

    public void setCurrentHeight(int currentHeight) {
        this.currentHeight = currentHeight;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public int getFps() {
        return fps;
    }

    private void checkFPS() {
        long currentFrameTime = System.nanoTime();
        numFramesInSecond++;
        if (currentFrameTime - lastFrameTime >= NANO_IN_SEC) {
            fps = numFramesInSecond;
            lastFrameTime = currentFrameTime;
            numFramesInSecond = 0;
        }
    }

    public boolean mouseClick() {
        return (getMouseClickX() > -1 && getMouseClickY() > -1);
    }

}
