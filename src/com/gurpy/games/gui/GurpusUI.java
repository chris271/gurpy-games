package com.gurpy.games.gui;

import com.gurpy.games.pojos.action.DrawAction;
import com.gurpy.games.pojos.component.RenderingComponent;
import com.gurpy.games.pojos.control.Direction;
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
    private final long NANO_IN_SEC = 1000000000;
    private long start = 0;
    private int numFramesInSecond = 0;
    private long lastFrameTime = 0;
    private volatile int fps = 0;
    private CopyOnWriteArrayList<UIEntity> guiElements = new CopyOnWriteArrayList<>();
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
        this.setPreferredSize(new Dimension(600, 600));
        this.setDoubleBuffered(true);

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

        final int SCALING = 1;
        renderingComponent = new RenderingComponent();
        TextElement fpsCounter = new TextElement(
                new Point2D.Double(10, 20),
                Color.BLACK,
                Color.RED,
                "FPS: 0",
                10.0);
        fpsCounter.setDisplay(true);
        addGuiElement(fpsCounter);

        CopyOnWriteArrayList<MenuItem> menuItems = new CopyOnWriteArrayList<>();
        menuItems.add(new MenuItem(
                new Point2D.Double(getPreferredSize().getWidth() / 2 - 200 * SCALING, getPreferredSize().getHeight() / 4),
                new Dimension(400 * SCALING, 50 * SCALING),
                Color.WHITE,
                Color.RED,
                "Play Game",
                5.0));
        menuItems.add(new MenuItem(
                new Point2D.Double(getPreferredSize().getWidth() / 2 - 200 * SCALING, getPreferredSize().getHeight() / 4 + 100),
                new Dimension(400 * SCALING, 50 * SCALING),
                Color.WHITE,
                Color.RED,
                "Options",
                5.0));
        menuItems.add(new MenuItem(
                new Point2D.Double(getPreferredSize().getWidth() / 2 - 200 * SCALING, getPreferredSize().getHeight() / 4 + 200),
                new Dimension(400 * SCALING, 50 * SCALING),
                Color.WHITE,
                Color.RED,
                "Controls",
                5.0));
        menuItems.add(new MenuItem(
                new Point2D.Double(getPreferredSize().getWidth() / 2 - 200 * SCALING, getPreferredSize().getHeight() / 4 + 300),
                new Dimension(400 * SCALING, 50 * SCALING),
                Color.WHITE,
                Color.RED,
                "Exit Game",
                5.0));
        TextElement menuTitle = new TextElement(
                new Point2D.Double(getPreferredSize().getWidth() / 3, getPreferredSize().getHeight() / 8),
                Color.WHITE,
                Color.BLACK,
                "Gurpus Maximus",
                24);
        addGuiElement(new Menu(menuTitle, menuItems, true));
        addGuiElement(new Player(
                new Point2D.Double(getPreferredSize().getWidth() / 2 - 50, getPreferredSize().getHeight() / 2 - 50),
                new Dimension(100 * SCALING, 100 * SCALING),
                Color.BLACK,
                Color.RED,
                15.0,
                5));


        lastFrameTime = System.nanoTime();

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
        for (UIEntity uiEntity : guiElements) {
            if (uiEntity.isDisplay())
                renderingComponent.performAction(new DrawAction(uiEntity, g2d));
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

    public CopyOnWriteArrayList<UIEntity> getGuiElements() {
        return guiElements;
    }

    private void addGuiElement(UIEntity uiEntity) {
        guiElements.add(uiEntity);
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

}
