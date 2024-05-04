package com.econeigigobhoood.sgb.view;

import java.util.Scanner;

import com.econeigigobhoood.sgb.controller.Controller;
import com.econeigigobhoood.sgb.controller.Misc;
import java.util.ArrayList;

public class BookRegister {
    
    @SuppressWarnings("resource")
    public static void callBookRegister() {
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

            MainMenu.baseMsgFunc("Cadastrar livros novos");

            System.out.println(
                    "Dados a serem preenchidos:\n*ID do livro\n*Nome do livro\n*Autor do livro\n*Quantidade de páginas\n\n");

            System.out.print("ID do livro: ");
            try {
                if (idLivro == 0) {
                    idLivro = scannerInput.nextInt();

                    if (idLivro < 1) {
                        Misc.clearScreen();
                        System.out.println("A ID precisa ser número 1 ou maior.");
                        Misc.delay(3);
                        Misc.clearScreen();
                        callBookRegister();
                    }
                }
            } catch (Exception scannerException) {
                System.out.println(Misc.SCANNER_INVALID_ERROR);
                System.out.println("Por favor digite SOMENTE número, que seja igual ou maior que 0");
                Misc.delay(2);
                Misc.clearScreen();
                scannerInput.nextLine();
                callBookRegister();
            }

            System.out.print("Nome do livro: ");
            try {
                if (nomeLivro.isEmpty()) {
                    nomeLivro = scannerInput.nextLine();

                    if (nomeLivro.isBlank()) {
                        Misc.clearScreen();
                        System.out.println("O nome do livro não pode ser só espaço em branco ou nada");
                        Misc.delay(2);
                        Misc.clearScreen();
                        callBookRegister();
                    }
                }
            } catch (Exception scannerException) {
                System.out.println(Misc.SCANNER_INPUT_ERROR);
                Misc.delay(2);
                Misc.clearScreen();
                scannerInput.nextLine();
                callBookRegister();
            }

            System.out.print("Autor do livro: ");
            try {
                if (autorLivro.isEmpty()) {
                    autorLivro = scannerInput.nextLine();

                    if (autorLivro.isBlank()) {
                        Misc.clearScreen();
                        System.out.println("O nome do autor do livro não pode ser só espaço em branco ou nada");
                        Misc.delay(2);
                        Misc.clearScreen();
                        callBookRegister();
                    }
                }
            } catch (Exception scannerException) {
                System.out.println(Misc.SCANNER_INPUT_ERROR);
                Misc.delay(2);
                Misc.clearScreen();
                scannerInput.nextLine();
                callBookRegister();
            }

            System.out.print("Quantidade de páginas: ");
            try {
                if (paginasLivro == 0) {
                    paginasLivro = scannerInput.nextInt();

                    if (paginasLivro < 0) {
                        Misc.clearScreen();
                        System.out.println("A quantidade de páginas precisa em número e igual 1 ou maior.");
                        Misc.delay(2);
                        Misc.clearScreen();
                        callBookRegister();
                    }
                }
            } catch (Exception scannerException) {
                System.out.println(Misc.SCANNER_INVALID_ERROR);
                System.out.println("Por favor digite SOMENTE número, que seja igual ou maior que 0");
                Misc.delay(2);
                Misc.clearScreen();
                scannerInput.nextLine();
                callBookRegister();
            }

            System.out.println("Livro a ser cadastrado: ");

            System.out.printf("ID: {0}", idLivro);
            System.out.printf("Nome do livro: {0}", nomeLivro);
            System.out.printf("Autor do livro: {0}", autorLivro);
            System.out.printf("Quantidade de páginas: {0}", paginasLivro);

            // Cria um arquivo temporario para tratativa de Scanner
            livroBuffer.add(Integer.toString(idLivro));
            livroBuffer.add(nomeLivro);
            livroBuffer.add(autorLivro);
            livroBuffer.add(Integer.toString(paginasLivro));
            refMisc.writeText("bookRegister", livroBuffer);

            System.out.println("Esta correto o cadastro? (S/N): ");
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

        else {
            Scanner scannerInput = new Scanner(System.in);

            // Carregar as informações do arquivo temporário
            idLivro = Integer.parseInt(livroBuffer.get(0));
            nomeLivro = livroBuffer.get(1);
            autorLivro = livroBuffer.get(2);
            paginasLivro = Integer.parseInt(livroBuffer.get(3));

            System.out.printf("ID: {0}", idLivro);
            System.out.printf("Nome do livro: {0}", nomeLivro);
            System.out.printf("Autor do livro: {0}", autorLivro);
            System.out.printf("Quantidade de páginas: {0}", paginasLivro);

            System.out.println("Esta correto o cadastro? (S/N): ");
            op = scannerInput.nextLine();
            op = op.toUpperCase();

            // Se condição for verdadeira os dados serão enviados
            if (op == "S") {
                refController.insertarLivro(idLivro, nomeLivro, autorLivro, paginasLivro, autorLivro, "true");
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
