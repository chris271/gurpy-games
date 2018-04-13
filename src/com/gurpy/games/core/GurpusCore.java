package com.gurpy.games.core;

import com.gurpy.games.gui.GurpusUI;
import com.gurpy.games.pojos.action.Action;
import com.gurpy.games.pojos.action.TranslationAction;
import com.gurpy.games.pojos.component.PhysicsComponent;
import com.gurpy.games.pojos.entities.Player;
import com.gurpy.games.pojos.entities.TextElement;
import com.gurpy.games.pojos.entities.UIElement;
import com.gurpy.games.pojos.entities.UIEntity;
import com.gurpy.games.utils.Logger;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.io.IOException;

public class GurpusCore implements Runnable{

    private final GurpusUI contentPane;
    private final static String OS = System.getProperty("os.name").toLowerCase();
    private boolean actionFlag = false;
    private boolean actionCompleteFlag = false;
    private PhysicsComponent physicsComponent;
    private Action currentAction;

    public GurpusCore(GurpusUI contentPane) {
        this.contentPane = contentPane;
        this.physicsComponent = new PhysicsComponent();
    }

    public void run() {

        while (contentPane.isShowing()) {

            updateCurrentGUIState();
            if(actionFlag) {
                try {
                    actionCompleteFlag = performForResult(currentAction);
                    if (actionCompleteFlag) {
                        Logger.info("Action Completed!");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    actionFlag = false;
                }
            }

        }

        System.exit(1);
    }

    private boolean performForResult(Action action) throws IOException {

        //Define resources...
        try {
            //Use resources...
        } catch (Exception e) {
            Logger.debug("Error: Unexpected Exception has occurred.");
            e.printStackTrace();
        } finally {
            //Close resources...
        }

        return true;
    }

    private void updateCurrentGUIState() {
        //Iterate over each element.
        for (UIEntity e : contentPane.getGuiElements()) {
            if (e instanceof Player) {
                Player player = (Player) e;
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
            if (e instanceof TextElement) {
                TextElement textElement = (TextElement)e;
                if (textElement.getText().contains("FPS")) {
                    textElement.setText("FPS: " + contentPane.getFps());
                }
            }
        }

        if (!contentPane.isShowing()) {
            Logger.error("Cannot find GUI!");
            System.exit(0);
        }
    }

    private void showInputInt() {
        Integer.parseInt(JOptionPane.showInputDialog(null, "Choose an integer.", 0));
    }

    private void showInputString() {
        JOptionPane.showInputDialog(null, "Choose a String.", "");
    }

}

