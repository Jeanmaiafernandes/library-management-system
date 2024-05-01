package com.econeigigobhoood.sgb.model;

//interface da Biblioteca
public interface Cadastro {
    void adicionarLivro(int Idlivro, String nome, String autor, int paginas);
    void listarLivros();
}
