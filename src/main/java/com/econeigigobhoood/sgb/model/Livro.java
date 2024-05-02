package com.econeigigobhoood.sgb.model;

public class Livro   {
    
    private static int Idlivro;
    private static String Nome;
    private static String Autor;
    private static int Paginas;
    private static String Status;
    

    public Livro(int Idlivro, String Nome, String Autor, int Paginas,String Status) {
        Livro.Idlivro = Idlivro;
        Livro.Nome = Nome;
        Livro.Autor = Autor;
        Livro.Paginas = Paginas;
        Livro.Status = Status;
    }

    // Getters
    public static int getIdlivro() {
        return Idlivro;
    }

    public static String getNome() {
        return Nome;
    }

    public static String getAutor() {
        return Autor;
    }

    public static int getPaginas() {
        return Paginas;
    }

    public static String getStatus() {
        return Status;
    }

    // Setters
    public static  int setIdlivro(int Idlivro) {
        return Livro .Idlivro = Idlivro;
    }

    public static String setNome(String Nome) {
        return Livro.Nome = Nome;
    }

    public static String setAutor(String Autor) {
        return Livro.Autor = Autor;
    }


    public static int setPaginas(int Paginas) {
        return Livro.Paginas = Paginas;
    }

    public static String setStatus(String Status) {
        return Livro.Status = Status;
    }

   

    
}
