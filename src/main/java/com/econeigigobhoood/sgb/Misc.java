package com.econeigigobhoood.sgb;

public class Misc {
    
    //Mensagens de erro para o usuário de acordo com cada caso.
    public static final String SCANNER_INPUT_ERROR = "ERROR: Unable to set user input: scanner.";
    public static final String DATABASE_CONNECTION_ERROR = "ERROR: Unable to connect to the database.";
    public static final String FILE_NOT_FOUND_ERROR = "ERROR: File not found.";
    
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void delay(int timeSeconds)
    {
        try {
            Thread.sleep(timeSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
