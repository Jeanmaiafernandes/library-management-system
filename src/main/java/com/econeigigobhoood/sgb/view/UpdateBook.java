package com.econeigigobhoood.sgb.view;

import com.econeigigobhoood.sgb.controller.Misc;

public class UpdateBook {
    public static void callUpdateBook()
    {
        MainMenu.baseMsgFunc("Alterar cadastro de livros");

        Misc.text("1 - Pesquisa por ID");
        Misc.text("2 - Pesquisar por nome do livro");

        Object rawInputUser = Misc.inputUser();
        int intInputUser = 0;

        if(rawInputUser instanceof Integer){
            intInputUser = (Integer) (rawInputUser);
        }
        else{
            Misc.text("Por favor digite apenas número");
        }

        switch (intInputUser) {
            case 1:
                
                break;
        
            default:
                break;
        }
    }
}