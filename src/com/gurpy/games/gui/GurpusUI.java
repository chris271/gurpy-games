package com.gurpy.games.gui;

import com.gurpy.games.pojos.control.TextAreaOutputStream;
import com.gurpy.games.pojos.entities.EntityTypes;
import com.gurpy.games.pojos.entities.UIElement;
import com.gurpy.games.pojos.control.UIMouseAdapter;
import com.gurpy.games.pojos.entities.UIEntity;
import com.gurpy.games.utils.Logger;

import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.PrintStream;
import java.util.ArrayList;

public class GurpusUI extends JPanel{
    //Allow for easy scaling of UIElements.
    private final double SCALING = 1.0;
    private ArrayList<UIEntity> guiElements = new ArrayList<>();
    private ArrayList<Integer> keyCodes = new ArrayList<>();
    private UIMouseAdapter uiMouseAdapter;
    private final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

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
        this.setPreferredSize(SCREEN_SIZE);
        this.setDoubleBuffered(true);

        //Add custom MouseListener using MouseAdapter.
        uiMouseAdapter = new UIMouseAdapter();
        addMouseListener(uiMouseAdapter);

        //KeyListeners only work on focused windows.
        this.setFocusable(true);
        this.requestFocusInWindow();

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
        checkUIElements(g2d);
        //Effectively recalls paintComponent(g);
        repaint();
    }

    /**
     * Method will iteratively draw all UIElements and print any font.
     */
    private void checkUIElements(Graphics g){
        //Press q or escape to exit simulation.
        if (keyCodes.contains(KeyEvent.VK_ESCAPE) || keyCodes.contains(KeyEvent.VK_Q)) {
            Logger.info("Exiting Simulation...");
            System.exit(1);
        }

        //Draw the title with Large Bold Blue Text.
        g.setFont(new Font("Large Font", Font.BOLD, 32));
        g.setColor(Color.BLUE);
        g.drawString("Java AWS S3 File Verifier.", 190, 50);

        //Iterate through each UIElement.
        for (UIEntity uiEntity : guiElements) {
            //Perform mouse updates on buttons only.
            if (uiEntity.getType() != EntityTypes.PLAYER) {
                performMouseUpdates(uiEntity);
            }
        }
    }

    private void performMouseUpdates(UIEntity guiElement){
        //Check to see if the mouse is clicked inside of the bounding box of an element and set clicked.
        /*if(uiMouseAdapter.getXDown() > guiElement.getX() &&
                uiMouseAdapter.getYDown() > guiElement.getY() &&
                uiMouseAdapter.getXDown() < guiElement.getX() + guiElement.getWidth() &&
                uiMouseAdapter.getYDown() < guiElement.getY() + guiElement.getHeight()){

        } else {
            guiElement.setClicked(false);
        }*/
    }

    /**
     * Gives access to guiElements to other class.
     */
    public ArrayList<UIEntity> getGuiElements(){
        return guiElements;
    }

    public void addConsoleToGUI() {
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

}
