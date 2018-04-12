package com.gurpy.games.core;

import com.gurpy.games.gui.GurpusUI;
import com.gurpy.games.utils.Logger;

import javax.swing.*;

public class Main {

    private static GurpusUI contentPane = null;
    private static GurpusCore processingThread = null;

    public static void main(String[] args) {

        Logger.info("Starting application...");
        Logger.info("Opening GUI...");
        JFileChooser fc = new JFileChooser();   // Instantiate the file chooser

        //Attempts to create a Thread which opens a JFrame window and add a graphical panel component.
        SwingUtilities.invokeLater(new Runnable() {
            /**
             * Runs the GUI in a separate thread as not to interrupt the simulation.
             */
            @Override
            public void run() {
                //Creates a new window named OS Sim
                JFrame frame = new JFrame("AWS S3 Utility");
                //Removes the JFrame on clicking close.
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                //Center the frame.
                frame.setLocationRelativeTo(null);
                contentPane = new GurpusUI();
                processingThread = new GurpusCore(contentPane);
                frame.setContentPane(contentPane);
                Logger.info("GUI Running.");
                frame.pack();
                frame.setVisible(true);
            }
        });

        //Ensure that the GUI thread has successfully started before starting the background thread.
        while (processingThread == null) {
            try {
                Thread.sleep(2000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        powerUpScanner();    // Start the Scanner thread

    }

    /**
     * Starts the background thread.
     */
    private static void powerUpScanner(){
        processingThread.run();
    }
}
