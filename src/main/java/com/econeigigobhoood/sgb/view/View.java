package com.econeigigobhoood.sgb.view;

import java.util.Scanner;

import com.econeigigobhoood.sgb.controller.Controller;
import com.econeigigobhoood.sgb.controller.Misc;
import java.util.ArrayList;

public class View {
    private MainMenu mainMenu;

    // Puxar a instancia pelo construtor
    // Ação realizada no application
    public View(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }
    
    public void callUpdateBook() {
        mainMenu.baseMsgFunc("Alterar cadastro de livros");

        Misc.text("1 - Pesquisa por ID");
        Misc.text("2 - Voltar a tela 'Cadastro de livros'");

        Object rawInputUser = Misc.inputUser();
        int intInputUser = 0;

        if(rawInputUser instanceof Integer) {
            intInputUser = (Integer) (rawInputUser);
        } else {
            Misc.clearScreen();
            Misc.text("Por favor digite apenas número");
            Misc.delay(3);
            callUpdateBook();
        }

        switch (intInputUser) {
            case 1 -> { searchID(); Misc.clearScreen(); }
            case 2 -> { Misc.clearScreen(); mainMenu.bookRegMenu(); } 
            default -> { 
                Misc.clearScreen();    
                Misc.text("Opção invalida");
                Misc.delay(3);
                callUpdateBook(); 
            }
        }
    }

    private void searchID() {
        mainMenu.baseMsgFunc("Pesquisa por ID");

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

    private void searchName() {
        mainMenu.baseMsgFunc("Pesquisa por nome do livro");

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

    @SuppressWarnings("resource")
    public void callBookRegister() {
        Misc refMisc = new Misc();
        Controller refController = new Controller();
        String op = "";
        ArrayList<String> livroBuffer = new ArrayList<String>();
        int idLivro = 0;
        String nomeLivro = "";
        String autorLivro = "";
        int paginasLivro = 0;

        livroBuffer = refMisc.readText("bookRegister");

        // Se o arquivo tmp estiver vazio, significa que é um novo cadastro
        if (livroBuffer.size() <= 0) {
            Scanner scannerInput = new Scanner(System.in);

            mainMenu.baseMsgFunc("Cadastrar livros novos");

            Misc.text(
                    "Dados a serem preenchidos:\n*ID do livro\n*Nome do livro\n*Autor do livro\n*Quantidade de páginas\n\n");

            Misc.text("ID do livro: ");
            try {
                if (idLivro == 0) {
                    idLivro = scannerInput.nextInt();

                    if (idLivro < 1) {
                        Misc.clearScreen();
                        Misc.text("A ID precisa ser número 1 ou maior.");
                        Misc.delay(3);
                        Misc.clearScreen();
                        callBookRegister();
                    }
                }
            } catch (Exception scannerException) {
                Misc.text(Misc.SCANNER_INVALID_ERROR);
                Misc.text("Por favor digite SOMENTE número, que seja igual ou maior que 0");
                Misc.delay(2);
                Misc.clearScreen();
                scannerInput.nextLine();
                callBookRegister();
            }

            Misc.text("Nome do livro: ");
            try {
                if (nomeLivro.isEmpty()) {
                    nomeLivro = scannerInput.nextLine();

                    if (nomeLivro.isBlank()) {
                        Misc.clearScreen();
                        Misc.text("O nome do livro não pode ser só espaço em branco ou nada");
                        Misc.delay(2);
                        Misc.clearScreen();
                        callBookRegister();
                    }
                }
            } catch (Exception scannerException) {
                Misc.text(Misc.SCANNER_INPUT_ERROR);
                Misc.delay(2);
                Misc.clearScreen();
                scannerInput.nextLine();
                callBookRegister();
            }

            Misc.text("Autor do livro: ");
            try {
                if (autorLivro.isEmpty()) {
                    autorLivro = scannerInput.nextLine();

                    if (autorLivro.isBlank()) {
                        Misc.clearScreen();
                        Misc.text("O nome do autor do livro não pode ser só espaço em branco ou nada");
                        Misc.delay(2);
                        Misc.clearScreen();
                        callBookRegister();
                    }
                }
            } catch (Exception scannerException) {
                Misc.text(Misc.SCANNER_INPUT_ERROR);
                Misc.delay(2);
                Misc.clearScreen();
                scannerInput.nextLine();
                callBookRegister();
            }

            Misc.text("Quantidade de páginas: ");
            try {
                if (paginasLivro == 0) {
                    paginasLivro = scannerInput.nextInt();

                    if (paginasLivro < 0) {
                        Misc.clearScreen();
                        Misc.text("A quantidade de páginas precisa em número e igual 1 ou maior.");
                        Misc.delay(2);
                        Misc.clearScreen();
                        callBookRegister();
                    }
                }
            } catch (Exception scannerException) {
                Misc.text(Misc.SCANNER_INVALID_ERROR);
                Misc.text("Por favor digite SOMENTE número, que seja igual ou maior que 0");
                Misc.delay(2);
                Misc.clearScreen();
                scannerInput.nextLine();
                callBookRegister();
            }

            Misc.text("Livro a ser cadastrado: ");

            Misc.text("ID: {0}", idLivro);
            Misc.text("Nome do livro: {0}", nomeLivro);
            Misc.text("Autor do livro: {0}", autorLivro);
            Misc.text("Quantidade de páginas: {0}", paginasLivro);

            // Cria um arquivo temporario para tratativa de Scanner
            livroBuffer.add(Integer.toString(idLivro));
            livroBuffer.add(nomeLivro);
            livroBuffer.add(autorLivro);
            livroBuffer.add(Integer.toString(paginasLivro));
            refMisc.writeText("bookRegister", livroBuffer);

            Misc.text("Esta correto o cadastro? (S/N): ");
            op = scannerInput.nextLine();
            op = op.toUpperCase();

            // Se condição for verdadeira os dados serão enviados
            if (op == "S") {
                mainMenu.bookRegMenu();
            } else if (op == "N") {
                Misc.clearScreen();
                refMisc.deleteFile("bookRegister");
                callBookRegister();
            } else {
                Misc.clearScreen();
                callBookRegister();
            }
        } else {
            Scanner scannerInput = new Scanner(System.in);

            // Carregar as informações do arquivo temporário
            idLivro = Integer.parseInt(livroBuffer.get(0));
            nomeLivro = livroBuffer.get(1);
            autorLivro = livroBuffer.get(2);
            paginasLivro = Integer.parseInt(livroBuffer.get(3));

            Misc.text("ID: {0}", idLivro);
            Misc.text("Nome do livro: {0}", nomeLivro);
            Misc.text("Autor do livro: {0}", autorLivro);
            Misc.text("Quantidade de páginas: {0}", paginasLivro);

            Misc.text("Esta correto o cadastro? (S/N): ");
            op = scannerInput.nextLine();
            op = op.toUpperCase();

            // Se condição for verdadeira os dados serão enviados
            if (op == "S") {
                
            } else if (op == "N") {
                Misc.clearScreen();
                refMisc.deleteFile("bookRegister");
                callBookRegister();
            } else {
                Misc.clearScreen();
                callBookRegister();
            }
        }
    }
}