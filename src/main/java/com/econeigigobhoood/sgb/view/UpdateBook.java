package com.econeigigobhoood.sgb.view;

import java.util.ArrayList;

import com.econeigigobhoood.sgb.controller.Misc;

public class UpdateBook {
    public static void callUpdateBook() {
        MainMenu.baseMsgFunc("Alterar cadastro de livros");

        Misc.text("1 - Pesquisa por ID");
        Misc.text("2 - Pesquisar por nome do livro");
        Misc.text("3 - Voltar a tela 'Cadastro de livros'");

        Object rawInputUser = Misc.inputUser();
        int intInputUser = 0;

        if (rawInputUser instanceof Integer) {
            intInputUser = (Integer) (rawInputUser);
        } else {
            Misc.clearScreen();
            Misc.text("Por favor digite apenas número");
            Misc.delay(3);
            callUpdateBook();
        }

        switch (intInputUser) {
            case 1:
                searchID();
                Misc.clearScreen();
                break;
            case 2:
                searchName();
                Misc.clearScreen();
                break;
            case 3:
                Misc.clearScreen();
                MainMenu.bookRegMenu();
                break;
            default:
                Misc.clearScreen();
                Misc.text("Opção invalida");
                Misc.delay(3);
                callUpdateBook();
                ;
                break;
        }
    }

    private static void searchID() {
        MainMenu.baseMsgFunc("Pesquisa por ID");

        Object rawInputUser = Misc.inputUser();
        int intInputUser = 0;

        ArrayList<Integer> idLivro = new ArrayList<Integer>();
        ArrayList<String> nomeLivro = new ArrayList<String>();

        if (rawInputUser instanceof Integer) {
            intInputUser = (Integer) (rawInputUser);
        } else {
            Misc.clearScreen();
            Misc.text("Por favor digite apenas número");
            Misc.delay(3);
            searchID();
        }

        // CONEXÃO COM BANCO DE DADOS por ID

        nomeLivro.addAll();

        for (int i = 0; i < idLivro.size(); i++) {
            Misc.text("ID: {0} | Nome: {1}", idLivro.get(i), nomeLivro.get(i));
        }
    }

    private static void searchName() {
        MainMenu.baseMsgFunc("Pesquisa por nome do livro");

        String StringInputUser = (String) (Misc.inputUser());

        ArrayList<Integer> idLivro = new ArrayList<Integer>();
        ArrayList<String> nomeLivro = new ArrayList<String>();

        // CONEXÃO COM BANCO DE DADOS por nome

        idLivro.addAll();

        for (int i = 0; i < nomeLivro.size(); i++) {
            Misc.text("ID: {0} | Nome: {1}", idLivro.get(i), nomeLivro.get(i));
        }
    }
}