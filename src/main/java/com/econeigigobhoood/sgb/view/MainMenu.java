package com.econeigigobhoood.sgb.view;

import java.util.Scanner;

import com.econeigigobhoood.sgb.controller.Misc;
import java.util.ArrayList;

public class MainMenu {

    public static ArrayList<String> listOptions = new ArrayList<String>();

    private static void msgDefault(int timeSec) {
        Misc.clearScreen();
        Misc.text("ATENÇÃO -> Opção inválida\n");
        Misc.delay(timeSec);
        Misc.clearScreen();
    }

    @SuppressWarnings("resource")
    private static void helpMenu() {
        Scanner scannerInput = new Scanner(System.in);

        Misc.text("**** Sistema de Gestão de Bibliotca ****\n");
        Misc.text("=========== Tela de ajuda ===========\n\n");
        Misc.text(
                "Para utilizar o Sistema de Gestão de Livros será apresentado opções de cada tela presente ao usuário, cada opção tem um número correspondente");
        Misc.text(
                "sendo assim para selecionar a opção desejada basta digitar o número desta opção, por exemplo:\n");

        Misc.text("1 - Carregar arquivo");
        Misc.text("O número 1 representa o número da opção e Carregar arquivo é a descrição da opção.\n");

        Misc.text(
                "Após digitar a opção desejada basta pressionar a tecla ENTER que será carregado a tela correspondente da função escolhida.\n");
        Misc.text("Pressione qualquer tecla para retornar ao menu principal...");

        try {
            scannerInput.nextLine();
        } catch (Exception scannerException) {
            Misc.text(Misc.SCANNER_INPUT_ERROR);
            System.exit(-1);
        }

        Misc.clearScreen();
        callMainMenu();
    }

    @SuppressWarnings("resource")
    private static String baseMsg(String nameScreen, ArrayList<String> opSelector) {
        String op = "";

        Scanner scannerInput = new Scanner(System.in);
        Misc.text("**** Sistema de Gestão de Bibliotca ****\n");

        Misc.text("Selecione a opção desejada digitando o número correspondente:\n");

        Misc.text("=========== %s ===========\n", nameScreen);
        Misc.text("Dica: Para receber ajuda escreva AJUDAR.\n");

        for (int i = 0; i < opSelector.size(); i++) {
            Misc.text(opSelector.get(i));
        }

        try {
            Misc.text("Número da opção: ");
            op = scannerInput.nextLine();
            op = op.toUpperCase();

        } catch (Exception scannerException) {
            Misc.text(Misc.SCANNER_INPUT_ERROR);
            Misc.delay(2);
            Misc.clearScreen();
            scannerInput.nextLine();
            callMainMenu();
        }

        return op;
    }

    protected static void baseMsgFunc(String nameScreen) {
        Misc.text("**** Sistema de Gestão de Bibliotca ****\n");

        Misc.text("=========== %s ===========\n\n", nameScreen);
        Misc.text("Dica: Para receber ajuda escreva AJUDAR.\n\n");
    }

    // Menu inicial é este aqui.
    public static void callMainMenu() {
        String op = "";
        listOptions.clear();
        listOptions.add("1 - Empréstimo de livros");
        listOptions.add("2 - Cadastro de livros");
        listOptions.add("3 - Consultar livros disponiveis");
        listOptions.add("4 - Sair do programa");

        op = baseMsg("Menu principal", listOptions);

        switch (op) {
            case "1":
                Misc.clearScreen();
                bookBorrow();
                break;

            case "2":
                Misc.clearScreen();
                bookRegMenu();
                break;
            case "3":
                break;
            case "4":
                Misc.clearScreen();
                Misc.text("Sistema fechado, até logo!\n\n");
                System.exit(0);
                break;
            case "AJUDAR":
                Misc.clearScreen();
                helpMenu();
                break;

            default:
                msgDefault(3);
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
                msgDefault(3);
                bookBorrow();
                break;
        }
    }

    // Opção 2
    public static void bookRegMenu() {
        String op = "";
        listOptions.clear();
        listOptions.add("1 - Cadastrar livros novos");
        listOptions.add("2 - Alterar cadastro de livros");
        listOptions.add("3 - Voltar ao menu anterior");

        op = baseMsg("Cadastro de livros", listOptions);

        switch (op) {
            case "1":
                Misc.clearScreen();
                BookRegister.callBookRegister();
                break;
            case "2":

                break;
            case "3":
                Misc.clearScreen();
                callMainMenu();
                break;
            default:
                msgDefault(3);
                bookBorrow();
                break;
        }
    }
}
