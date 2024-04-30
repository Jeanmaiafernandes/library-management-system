package com.econeigigobhoood.sgb;

import java.util.Scanner;
import java.util.ArrayList;

public class MainMenu {

    public static ArrayList<String> listOptions = new ArrayList<String>();

    @SuppressWarnings("resource")
    private static int baseMsg(String nameScreen, ArrayList<String> opSelector) {
        int op = 0;
        Scanner scannerInput = new Scanner(System.in);

        System.out.println("**** SISTEMA DE GESTÃO DE BIBLÍOTECA ****\n\n\n");

        System.out.printf("=========== %s ===========\n\n",nameScreen);
        System.out.println("Dica: Para receber ajuda escreva AJUDAR.\n");

        for (int i = 0; i < opSelector.size(); i++) {
            System.out.println(opSelector.get(i));
        }

        System.out.print("Número da opção: ");
        try {
            op = scannerInput.nextInt();
        } catch (Exception scannerException) {
            System.out.printf(Misc.SCANNER_INPUT_ERROR);
            System.exit(-1);
        }

        return op;
    }

    // Menu inicial é este aqui.
    public static void callMainMenu() {
        int op = 0;
        listOptions.clear();
        listOptions.add("1 - Empréstimo de livros");
        listOptions.add("2 - Cadastro de livros");
        listOptions.add("3 - Consultar livros disponiveis");
        listOptions.add("4 - Sair do programa");

        op = baseMsg("Menu principal", listOptions);

        switch (op) {
            case 1:
                Misc.clearScreen();
                bookBorrow();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                Misc.clearScreen();
                System.out.println("Sistema fechado, até logo!\n\n");
                System.exit(0);
                break;

            default:
                System.out.println("ATENÇÃO -> Opção inválida\n");
                callMainMenu();
                break;
        }
    }

    // Opção 1
    public static void bookBorrow() {
        int op = 0;
        listOptions.clear();
        listOptions.add("1 - Entrega de livros");
        listOptions.add("2 - Devolução de livros");
        listOptions.add("3 - Voltar ao menu anterior");

        op = baseMsg("Empréstimo de livros", listOptions);

        switch (op) {
            case 1:

                break;
            case 2:

                break;
            case 3:
                Misc.clearScreen();
                callMainMenu();
                break;

            default:
                System.out.println("ATENÇÃO -> Opção inválida\n");
                bookBorrow();
                break;
        }
    }

    //Opção 2
    public static void bookRegister()
    {
        int op = 0;
        listOptions.clear();
        listOptions.add("1 - Entrega de livros");
        listOptions.add("2 - Devolução de livros");
        listOptions.add("3 - Voltar ao menu anterior");

        op = baseMsg("Cadastro de livros", listOptions);

        switch (op) {
            case 1:

                break;
            case 2:

                break;
            case 3:
                Misc.clearScreen();
                callMainMenu();
                break;

            default:
                System.out.println("ATENÇÃO -> Opção inválida\n");
                bookBorrow();
                break;
        }
    }

}
