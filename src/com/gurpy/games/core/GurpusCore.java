package com.gurpy.games.core;

import com.gurpy.games.gui.GurpusUI;
import com.gurpy.games.pojos.component.PhysicsComponent;
import com.gurpy.games.pojos.entities.UIElement;
import com.gurpy.games.pojos.entities.UIEntity;
import com.gurpy.games.utils.Logger;

import javax.swing.*;
import java.io.IOException;

public class GurpusCore implements Runnable{

    private final GurpusUI contentPane;
    private final static String OS = System.getProperty("os.name").toLowerCase();
    private boolean actionFlag = false;
    private boolean actionCompleteFlag = false;
    private PhysicsComponent physicsComponent;

    public GurpusCore(GurpusUI contentPane) {
        this.contentPane = contentPane;
        this.physicsComponent = new PhysicsComponent();
    }

    public void run() {

        while (contentPane.isShowing()) {

            updateCurrentGUIState();
            if(actionFlag) {
                try {
                    actionCompleteFlag = actionMethod("");
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

    public boolean actionMethod(String params) throws IOException {

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

