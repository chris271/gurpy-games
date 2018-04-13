package com.gurpy.games.core;

import com.gurpy.games.gui.GurpusUI;
import com.gurpy.games.utils.Logger;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        final GurpusUI contentPane = new GurpusUI();
        final GurpusCore processingThread = new GurpusCore(contentPane);
        Logger.info("Starting application...");
        Logger.info("Opening GUI...");

        //Attempts to create a Thread which opens a JFrame window and add a graphical panel component.
        SwingUtilities.invokeLater(new Runnable() {
            /**
             * Runs the GUI in a separate thread as not to interrupt the simulation.
             */
            @Override
            public void run() {
                //Creates a new window named OS Sim
                JFrame frame = new JFrame("Gurpus Maximus");
                //Removes the JFrame on clicking close.
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setContentPane(contentPane);
                Logger.info("GUI Running.");
                frame.pack();
                frame.requestFocus();
                frame.setVisible(true);
            }
        });

        //Ensure that the GUI thread has successfully started before starting the background thread.
        while (!contentPane.isShowing()) {
            try {
                Thread.sleep(2000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        processingThread.run();    // Start the Scanner thread

    }

}
