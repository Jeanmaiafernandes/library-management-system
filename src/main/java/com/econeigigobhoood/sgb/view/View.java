package com.econeigigobhoood.sgb.view;

import com.econeigigobhoood.sgb.controller.Controller;
import com.econeigigobhoood.sgb.controller.Misc;
import com.econeigigobhoood.sgb.model.Livro;

import java.util.Scanner;

public class View {
    private MainMenu mainMenu;
    private Controller controller;
    private Scanner scanner;

    // Puxar a instancia pelo construtor
    // Ação realizada no application
    public View(MainMenu mainMenu, Controller controller, Scanner scanner) {
        this.mainMenu = mainMenu;
        this.controller = controller;
        this.scanner = scanner;
    }
    
    public void searchID() {
        mainMenu.baseMsgFunc(" Buscador de livro ");

        Misc.text("Insira o ID do livro a ser consultado: ");
        int id = scanner.nextInt();
        Livro livro = controller.buscaLivro(id);
        if(livro != null) {
            System.out.println(">>> Detalhes do livro <<<");
            System.out.println("ID " + livro.getIdlivro());
            System.out.println("Nome: " + livro.getNome());
            System.out.println("Autor: " + livro.getAutor());
            System.out.println("Número de páginas: " + livro.getPaginas());

            mainMenu.callMainMenu();
        } else {
            Misc.clearScreen();
            Misc.text("Livro não encontrado ou ID inexistente");
            Misc.delay(3);

            mainMenu.callMainMenu();
        }
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

    public void callBookRegister() {
        // Se o arquivo tmp estiver vazio, significa que é um novo cadastro
        mainMenu.baseMsgFunc("Cadastrar livros novos");
        
        String op = "";

        Misc.text("Dados a serem preenchidos:\n*Nome do livro\n*Autor do livro\n*Quantidade de páginas\n\n");

        Misc.text("Nome do livro: ");
        String nomeLivro = scanner.nextLine();
        Misc.text("Autor do livro: ");
        String autorLivro = scanner.nextLine();
        Misc.text("Quantidade de páginas: ");
        int paginasLivro = scanner.nextInt();

        Misc.text("Livro a ser cadastrado: ");
        System.out.println("Nome do livro: " + nomeLivro);
        System.out.println("Autor do livro: " + autorLivro);
        System.out.println("Quantidade de páginas: " + paginasLivro);

        Misc.text("Esta correto o cadastro? (S/N): ");
        scanner.nextLine(); // Limpar buffer
        op = scanner.nextLine();
        op = op.toUpperCase();

        // Se condição for verdadeira os dados serão enviados
        if (op.equals("S")) {
            // Cadastrando livro no Banco de Dados
            Livro livro = new Livro(nomeLivro, autorLivro, paginasLivro);
            controller.insertarLivro(livro);
            Misc.text("Livro cadastrado com sucesso!");
            mainMenu.callMainMenu();
        } else if (op.equals("N")) {
            Misc.clearScreen();
            Misc.text("Operação cancelada!");
            callBookRegister();
        } else {
            Misc.clearScreen();
            Misc.text("Escolha invalida, operação cancelada!");
            callBookRegister();
        }
    }

    public void deleteAll() {
        System.out.println("Essa operação deletará TODOS OS DADOS inseridos no banco de dados, tem certeza que deseja realizar essa operação? [S/N]: ");
        String op = scanner.nextLine();
        op = op.toUpperCase();
        
        if (op.equals("S")) {
            controller.limpaBancoH2();
            mainMenu.callMainMenu();
        } else if (op.equals("N")) {
            Misc.clearScreen();
            Misc.text("Operação cancelada!");
            mainMenu.callMainMenu();
        } else {
            Misc.clearScreen();
            Misc.text("Escolha invalida, operação cancelada!");
            mainMenu.callMainMenu();
        }
    }
}