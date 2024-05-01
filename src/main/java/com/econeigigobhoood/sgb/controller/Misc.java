package com.econeigigobhoood.sgb.controller;

public class Misc {
    
    public static final String SCANNER_INPUT_ERROR = "ERROR: Unable to set user input on Scanner.";
    public static final String SCANNER_INVALID_ERROR = "ERROR: user input was invalid on Scanner. Wrong variable type?";
    public static final String DATABASE_CONNECTION_ERROR = "ERROR: Unable to connect to the database.";
    public static final String FILE_NOT_FOUND_ERROR = "ERROR: File not found.";
    
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void delay(int timeSeconds)
    {
        timeSeconds = timeSeconds * 1000;
        try {
            Thread.sleep(timeSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
