package com.econeigigobhoood.sgb.Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class Biblioteca implements Cadastro, Emprestar,Tables {
    protected String Nome;
    protected String Autor;
    protected int Paginas;
    protected String Status;

    private List<Livro> biblioteca;

    public Biblioteca() {
        this.biblioteca = new ArrayList<>();
    }

    
    public void adicionarLivro(int Idlivro,String Nome, String Autor, int Paginas) {
        Livro livro = new Livro(Idlivro,Nome, Autor, Paginas);
        biblioteca.add(livro);
        System.out.println("Livro cadastrado com sucesso!");
    }

  
    public void listarLivros() {
        for (Livro livro : biblioteca) {
            System.out.println(livro);
        }
    }


    public void emprestar(String Status,int IdLvro){
        
    }

    public void devolver(String Status,int IdLvro){

    }

    public void table(){
      String url = "jdbc:h2:mem:testdb";
        String user = "sa";
        String password = "";

   
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexão estabelecida com sucesso!");
            String createTableSQL = "CREATE TABLE Livros (" +
                                    "idLivro INT PRIMARY KEY," +
                                    "Nome VARCHAR(255)," +
                                    "autor VARCHAR(255)," +
                                    "Paginas," +
                                    "Status,"+
                                    ")";


            try (PreparedStatement pstmt = connection.prepareStatement(createTableSQL)) {
             
                pstmt.executeUpdate();

                System.out.println("Tabela Criada com Sucesso");
            
            }
      
            connection.close();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
    
}



