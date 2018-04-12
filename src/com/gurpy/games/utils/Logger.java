package com.gurpy.games.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple logging utility for formatting prints to the console. This
 * utility allows for four different levels of messages: debug, warn, info,
 * and error.
 *
 * @author Christopher Butler
 */
public final class Logger {

    private static final SimpleDateFormat STANDARD_FORMATTER = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss.SSS");

    /**
     * Default constructor. This is made private so it cannot be
     * instantiated. There are only class methods here.
     */
    private Logger(){}

    /**
     * Print a debugging message to the console.
     *
     * @param message message to print
     */
    public static void debug(String message) {
        System.out.println("[DEBUG] at " + STANDARD_FORMATTER.format(new Date()) + " - " + message);
    }

    /**
     * Print a warning message to the console.
     *
     * @param message message to print
     */
    public static void warn(String message) {
        System.out.println("[WARNING] at " + STANDARD_FORMATTER.format(new Date()) + " - " + message);
    }

    /**
     * Print an information message to the console.
     *
     * @param message message to print
     */
    public static void info(String message) {
        System.out.println("[INFO] at " + STANDARD_FORMATTER.format(new Date()) + " - " + message);
    }

    /**
     * Print an error message to the console.
     *
     * @param message message to print
     */
    public static void error(String message) {
        System.out.println("[ERROR] at " + STANDARD_FORMATTER.format(new Date()) + " - " + message);
    }
}