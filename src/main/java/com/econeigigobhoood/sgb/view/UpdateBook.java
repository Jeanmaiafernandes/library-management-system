package com.econeigigobhoood.sgb.view;

import com.econeigigobhoood.sgb.controller.Misc;

public class UpdateBook {
    public static void callUpdateBook()
    {
        MainMenu.baseMsgFunc("Alterar cadastro de livros");

        Misc.text("1 - Pesquisa por ID");
        Misc.text("2 - Pesquisar por nome do livro");
        Misc.text("3 - Voltar a tela 'Cadastro de livros'");

        Object rawInputUser = Misc.inputUser();
        int intInputUser = 0;

        if(rawInputUser instanceof Integer){
            intInputUser = (Integer) (rawInputUser);
        }
        else{
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
            callUpdateBook();;
                break;
        }
    }

    private static void searchID(){
        MainMenu.baseMsgFunc("Pesquisa por ID");

        Object rawInputUser = Misc.inputUser();
        int intInputUser = 0;

        if(rawInputUser instanceof Integer){
            intInputUser = (Integer) (rawInputUser);
        }
        else{
            Misc.clearScreen();
            Misc.text("Por favor digite apenas número");
            Misc.delay(3);
            searchID();
        }

        //conectar ao banco de dados
    }

    private static void searchName(){
        MainMenu.baseMsgFunc("Pesquisa por nome do livro");

        Object rawInputUser = Misc.inputUser();
        String StringInputUser = "";

        if(rawInputUser instanceof Integer){
            StringInputUser = (String) (rawInputUser);
        }
        else{
            Misc.clearScreen();
            Misc.text("Por favor apenas número");
            Misc.delay(3);
            searchID();
        }

        //conectar ao banco de dados
    }
}