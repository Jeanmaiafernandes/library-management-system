package com.econeigigobhoood.sgb;

import java.util.Scanner;
import java.util.ArrayList;

public class MainMenu {

    public static ArrayList<String> listOptions = new ArrayList<String>();

    private static int baseMsg(String nameScreen, ArrayList<String> opSelector)
    {
        int op = 0;
        opSelector = new ArrayList<String>();
        
        try (Scanner scannerInput = new Scanner(System.in)) {
            System.out.println("**** Sistema de Gestão de Bibliotca ****\n");

            System.out.println("Selecione a opção desejada digitando o número correspondente:\n");
            System.out.println(nameScreen);

            for(int i = 0; i < opSelector.size(); i++)
            {
                System.out.println(opSelector.get(i));
            }

            System.out.print("Número da opção: ");
            op = scannerInput.nextInt();
        } catch (Exception scannerException) {
            System.out.printf("ERROR: Unable to cast scannerInput: baseMsg.");
            System.exit(-1);
        }

        return op;
    }
    
    //Menu inicial é este aqui.
    public static void callMainMenu()
    {
        int op = 0;
        listOptions.clear();
        listOptions.add("1 - Empréstimo de livros");
        listOptions.add("2 - Cadastro de livros");
        listOptions.add("3 - Consultar livros disponiveis");
        
        op = baseMsg("Menu principal", listOptions);

        switch (op) {
            case 1:
                Utils.clearScreen();
                bookBorrow();
                break;
        
            default:
                System.out.println("ATENÇÃO -> Opção inválida");
                break;
        }
    }

    //Opção 1
    public static void bookBorrow()
    {
        int op = 0;
        listOptions.clear();
        listOptions.add("1 - Saída de livros");
        listOptions.add("2 - Devolução de livros");
        listOptions.add("3 - Voltar ao menu anterior");

        op = baseMsg("Menu principal", listOptions);

        switch (op) {
            case 1:
                
                break;
        
            default:
                break;
        }
    }
    
}
