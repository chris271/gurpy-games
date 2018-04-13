package com.gurpy.games.gui;

import com.gurpy.games.pojos.action.DrawAction;
import com.gurpy.games.pojos.action.UIAction;
import com.gurpy.games.pojos.component.RenderingComponent;
import com.gurpy.games.pojos.control.TextAreaOutputStream;
import com.gurpy.games.pojos.control.UIMouseListener;
import com.gurpy.games.pojos.entities.EntityTypes;
import com.gurpy.games.pojos.entities.Player;
import com.gurpy.games.pojos.entities.TextElement;
import com.gurpy.games.pojos.entities.UIEntity;
import com.gurpy.games.utils.Logger;

import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.io.PrintStream;
import java.util.ArrayList;

public class GurpusUI extends JPanel{
    //Allow for easy scaling of UIElements.
    private final double SCALING = 1.0;
    private int fps = 0;
    private int numFramesInSecond = 0;
    private long lastFrameTime = 0;
    private long currentFrameTime = 0;
    private ArrayList<UIEntity> guiElements = new ArrayList<>();
    private ArrayList<Integer> keyCodes = new ArrayList<>();
    private UIMouseListener uiMouseListener;
    private RenderingComponent renderingComponent;
    public final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    public final Dimension PANEL_SIZE = new Dimension(600, 600);

    /**
     * Custom constructor for OSPanel Object.
     *
     * Creates and adds all UIElements to an ArrayList.
     * Sets the children for each button.
     *
     */
    public GurpusUI() {
        //Self explanatory
        this.setBackground(Color.WHITE);
        this.setPreferredSize(PANEL_SIZE);
        this.setDoubleBuffered(true);

        //Add custom MouseListeners.
        uiMouseListener = new UIMouseListener();
        addMouseListener(uiMouseListener);
        addMouseMotionListener(uiMouseListener);
        addMouseWheelListener(uiMouseListener);

        //KeyListeners only work on focused windows.
        this.setFocusable(true);
        this.requestFocusInWindow();

        //addConsoleToGUI(); //For debugging purposes...

        //Anonymous class for KeyListener.
        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                //Add the key to check if it is one of the escape keys ("q" and "esc").
                keyCodes.add(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //Sometimes multiple instances of the keycode get added so remove all.
                while(keyCodes.contains(e.getKeyCode()))
                    keyCodes.remove(keyCodes.indexOf(e.getKeyCode()));
            }

            @Override
            public void keyTyped (KeyEvent e) {
                //Must be implemented to satisfy KeyListener.
            }
        });

        renderingComponent = new RenderingComponent();
        addGuiElement(new Player(
                new Point2D.Double(PANEL_SIZE.getWidth() / 2 - 50, PANEL_SIZE.getHeight() / 2 - 50),
                new Dimension(100, 100),
                Color.BLACK,
                Color.WHITE,
                10.0,
                .1));
        addGuiElement(new TextElement(
                new Point2D.Double(10, 20),
                Color.BLACK,
                Color.RED,
                "FPS: 0",
                10.0));

        lastFrameTime = System.currentTimeMillis();

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
            renderingComponent.performAction(new DrawAction(uiEntity, g2d));
        }

        //Method to update current FPS.
        checkFPS(g2d);
        //Effectively recalls paintComponent(g);
        repaint();
    }

    public ArrayList<UIEntity> getGuiElements() {
        return guiElements;
    }

    public void addGuiElement(UIEntity uiEntity) {
        guiElements.add(uiEntity);
    }

    public ArrayList<Integer> getKeyCodes(){
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

    private void addConsoleToGUI() {
        //Adds a text area with 20 rows and 20 cols.
        JTextArea textArea = new JTextArea(20, 20);
        //Set up the custom OutputStream attached to the textArea and title it.
        TextAreaOutputStream guiOutputStream = new TextAreaOutputStream(textArea, "Application Output");
        //Allow adding of new components to the panel in particular positions.
        setLayout(new BorderLayout());
        //Add the textArea object to the bottom of the page
        add(new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.SOUTH);
        //Hijack System.out console and add it to the GUI.
        System.setOut(new PrintStream(guiOutputStream));
    }

    private void checkFPS(Graphics2D g2d) {
        currentFrameTime = System.currentTimeMillis();
        numFramesInSecond++;
        if (currentFrameTime - lastFrameTime >= 1000) {
            fps = numFramesInSecond;
            lastFrameTime = currentFrameTime;
            numFramesInSecond = 0;
        }
    }

}
