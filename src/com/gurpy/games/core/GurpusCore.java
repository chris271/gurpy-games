package com.gurpy.games.core;

import com.gurpy.games.gui.GurpusUI;
import com.gurpy.games.pojos.action.TranslationAction;
import com.gurpy.games.pojos.component.PhysicsComponent;
import com.gurpy.games.pojos.entities.*;
import com.gurpy.games.pojos.entities.Menu;
import com.gurpy.games.pojos.entities.MenuItem;
import com.gurpy.games.utils.Logger;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class GurpusCore implements Runnable{

    private final GurpusUI contentPane;
    private final static String OS = System.getProperty("os.name").toLowerCase();
    private boolean isMenu = true;
    private PhysicsComponent physicsComponent;

    GurpusCore(GurpusUI contentPane) {
        this.contentPane = contentPane;
        this.physicsComponent = new PhysicsComponent();
    }

    public void run() {

        Logger.info("Running on " + OS);
        while (contentPane.isShowing()) {
            updateCurrentGUIState();
        }

        System.exit(1);
    }

    private void updateCurrentGUIState() {
        //Iterate over each element.
        for (Entity e : contentPane.getGuiElements()) {
            if (e instanceof Player) {
                Player player = (Player) e;
                if (!isMenu) {
                    checkPlayerMovement(player);
                    ((Player)e).setDisplay(true);
                } else {
                    ((Player)e).setDisplay(false);
                }
            }
            if (e instanceof TextElement) {
                TextElement textElement = (TextElement)e;
                if (textElement.getText().contains("FPS")) {
                    textElement.setText("FPS: " + contentPane.getFps());
                } else if (!isMenu) {
                    ((TextElement)e).setDisplay(true);
                } else {
                    ((TextElement)e).setDisplay(false);
                }
            }
            if (e instanceof Menu) {
                Menu menu = (Menu)e;
                if (menu.isDisplay()) {
                    for (MenuItem item : menu.getMenuItems()) {
                        Rectangle2D.Double itemRect = new Rectangle2D.Double(
                                item.getX(), item.getY(), item.getWidth(), item.getHeight());
                        if (itemRect.contains(contentPane.getMouseX(), contentPane.getMouseY())) {
                            item.setSelected(true);
                        } else {
                            item.setSelected(false);
                        }
                        if (item.isSelected() && contentPane.getMouseClickX() > -1 && contentPane.getMouseClickY() > -1) {
                            if (item.getItemText().equalsIgnoreCase("Play Game")) {
                                Logger.info("Starting Game.");
                                isMenu = false;
                                menu.setDisplay(false);
                                contentPane.setBackground(Color.WHITE);
                            } else if (item.getItemText().equalsIgnoreCase("Options")) {
                                Logger.info("In Options.");
                                menu.setDisplay(false);
                            } else if (item.getItemText().equalsIgnoreCase("Controls")) {
                                Logger.info("In Controls.");
                                menu.setDisplay(false);
                            } else if (item.getItemText().equalsIgnoreCase("Exit Game")) {
                                Logger.info("Exiting Game.");
                                System.exit(1);
                            }
                        }
                    }
                } else {
                    if (contentPane.getKeyCodes().contains(KeyEvent.VK_ESCAPE)) {
                        contentPane.setBackground(Color.BLACK);
                        isMenu = true;
                        ((Menu)e).setDisplay(true);
                    }
                }
            }
        }

        if (!contentPane.isShowing()) {
            Logger.error("Cannot find GUI!");
            System.exit(0);
        }
    }

    private void checkPlayerMovement(Player player) {
        if (contentPane.getKeyCodes().contains(KeyEvent.VK_LEFT)) {
            physicsComponent.performAction(new TranslationAction(player,
                    new Point2D.Double(player.getX() - player.getHspeed(), player.getY())));
        }
        if (contentPane.getKeyCodes().contains(KeyEvent.VK_RIGHT)) {
            physicsComponent.performAction(new TranslationAction(player,
                    new Point2D.Double(player.getX() + player.getHspeed(), player.getY())));
        }
        if (contentPane.getKeyCodes().contains(KeyEvent.VK_UP)) {
            physicsComponent.performAction(new TranslationAction(player,
                    new Point2D.Double(player.getX(), player.getY() - player.getVspeed())));
        }
        if (contentPane.getKeyCodes().contains(KeyEvent.VK_DOWN)) {
            physicsComponent.performAction(new TranslationAction(player,
                    new Point2D.Double(player.getX(), player.getY() + player.getVspeed())));
        }
    }

}

