package com.econeigigobhoood.sgb.view;

import java.util.Scanner;
import com.econeigigobhoood.sgb.controller.Misc;
import java.util.ArrayList;

public class MainMenu {

    public static ArrayList<String> listOptions = new ArrayList<String>();

    @SuppressWarnings("resource")
    private static void helpMenu() {
        Scanner scannerInput = new Scanner(System.in);

        System.out.println("**** Sistema de Gestão de Bibliotca ****\n");
        System.out.printf("=========== Tela de ajuda ===========\n\n");
        System.out.println(
                "Para utilizar o Sistema de Gestão de Livros será apresentado opções de cada tela presente ao usuário, cada opção tem um número correspondente");
        System.out.println(
                "sendo assim para selecionar a opção desejada basta digitar o número desta opção, por exemplo:\n");
        System.out.println("1 - Carregar arquivo");
        System.out.println("O número 1 representa o número da opção e Carregar arquivo é a descrição da opção.\n");
        System.out.println(
                "Após digitar a opção desejada basta pressionar a tecla ENTER que será carregado a tela correspondente da função escolhida.\n");
        System.out.println("Pressione qualquer tecla para retornar ao menu principal...");

        try {
            scannerInput.nextLine();
        } catch (Exception scannerException) {
            System.out.printf(Misc.SCANNER_INPUT_ERROR);
            System.exit(-1);
        }

        Misc.clearScreen();
        callMainMenu();
    }

    @SuppressWarnings("resource")
    private static String baseMsg(String nameScreen, ArrayList<String> opSelector) {
        String op = "";

        Scanner scannerInput = new Scanner(System.in);
        System.out.println("**** Sistema de Gestão de Bibliotca ****\n");

        System.out.println("Selecione a opção desejada digitando o número correspondente:\n");

        System.out.printf("=========== %s ===========\n\n", nameScreen);
        System.out.println("Dica: Para receber ajuda escreva AJUDAR.\n");

        for (int i = 0; i < opSelector.size(); i++) {
            System.out.println(opSelector.get(i));
        }

        try {
            System.out.print("Número da opção: ");
            op = scannerInput.nextLine();
            op = op.toUpperCase();

        } catch (Exception scannerException) {
            System.out.printf(Misc.SCANNER_INPUT_ERROR);
            System.exit(-1);
        }

        return op;
    }

    // Menu inicial é este aqui.
    public static void callMainMenu() {
        String op = "";
        listOptions.clear();
        listOptions.add("1 - Empréstimo de livros");
        listOptions.add("2 - Cadastro de livros");
        listOptions.add("3 - Consultar livros disponiveis");

        op = baseMsg("Menu principal", listOptions);

        switch (op) {
            case "1":
                Misc.clearScreen();
                bookBorrow();
                break;

            case "2":
                break;
            case "3":
                break;
            case "4":
                Misc.clearScreen();
                System.out.println("Sistema fechado, até logo!\n\n");
                System.exit(0);
                break;
            case "AJUDAR":
                Misc.clearScreen();
                helpMenu();
                break;

            default:
                System.out.println("ATENÇÃO -> Opção inválida\n");
                callMainMenu();
                break;
        }
    }

    // Opção 1
    public static void bookBorrow() {
        String op = "";
        listOptions.clear();
        listOptions.add("1 - Entrega de livros");
        listOptions.add("2 - Devolução de livros");
        listOptions.add("3 - Voltar ao menu anterior");

        op = baseMsg("Empréstimo de livros", listOptions);

        switch (op) {
            case "1":

                break;
            case "2":

                break;
            case "3":
                Misc.clearScreen();
                callMainMenu();
                break;
            default:
                System.out.println("ATENÇÃO -> Opção inválida\n");
                bookBorrow();
                break;
        }
    }

    public static void bookRegister() {
        String op = "";
        listOptions.clear();
        listOptions.add("1 - Entrega de livros");
        listOptions.add("2 - Devolução de livros");
        listOptions.add("3 - Voltar ao menu anterior");

        op = baseMsg("Cadastro de livros", listOptions);

        switch (op) {
            case "1":

                break;
            case "2":

                break;
            case "3":
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
