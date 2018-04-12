package com.gurpy.games.pojos.control;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Custom OutputStream class to hijack Console output.
 */
public final class TextAreaOutputStream extends OutputStream {

    //The text area in the JPanel
    private final JTextArea textArea;
    //StringBuilder for each line of output.
    private final StringBuilder sb = new StringBuilder();
    //The name of the Stream.
    private final String title;

    public TextAreaOutputStream(final JTextArea textArea, String title) {
        this.textArea = textArea;
        this.title = title;
        sb.append(title);
        sb.append(">> ");
    }

    @Override
    public void write(int b) throws IOException {

        //if \r then do nothing.
        if (b == '\r')
            return;

        //if there is a new line of console output then append it.
        if (b == '\n') {
            final String text = sb.toString() + "\n";
            //As not to interfere with the runtime of the JPanel.
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    textArea.append(text);
                }
            });
            //Reset the StringBuilder to be on the next line.
            sb.setLength(0);
            sb.append(title);
            sb.append(">> ");
            return;
        }

        sb.append((char) b);
    }
}