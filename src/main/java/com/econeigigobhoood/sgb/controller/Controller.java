package com.econeigigobhoood.sgb.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;



import com.econeigigobhoood.sgb.model.Livro;
import com.econeigigobhoood.sgb.model.Tables;


public  class Controller implements Tables {
    private DefaultTableModel modelo = new DefaultTableModel();
    private Connection conexion;
       

    @Override
    public boolean hayConection() {
        return (conexion != null);
    }

    @Override
    public Connection conectar() throws SQLException {
        try {
            Class.forName("org.h2.Driver");
            // Estabelece a conexão com o banco de dados H2 em memória
            conexion = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return conexion;
    }

    @Override
    public ResultSet executarSQL(String consultaSQL) throws SQLException {
        Statement sql = conexion.createStatement();
        return sql.executeQuery(consultaSQL);
    }

    @Override
    public boolean executarAtualizacaoSQL(String comandoSQL) throws SQLException {
        PreparedStatement sql = conexion.prepareStatement(comandoSQL);
        System.out.println(sql);
        return sql.executeUpdate() != 0;
    }

    @Override
    public void desconectar() throws SQLException {
        if (conexion != null) {
            conexion.close();
            conexion = null;
        }
    }
    
    public DefaultTableModel table() throws SQLException {
        //DefaultTableModel modelo = new DefaultTableModel(); // Inicializa o modelo de tabela
    
        try {
            conectar();
            ResultSet resultado = executarSQL("SELECT IdLivro, Nome, Autor, Paginas, Status FROM Livros;"); // Consulta SQL para selecionar todos os livros
            if (resultado != null) {
                while (resultado.next()) {
                    int IdLivro = resultado.getInt("IdLivro");
                    String Nome = resultado.getString("Nome");
                    String Autor = resultado.getString("Autor");
                    int Paginas = resultado.getInt("Paginas");
                    String Status = resultado.getString("Status");
    
                    Object[] fila = { IdLivro, Nome, Autor, Paginas, Status };
                    modelo.addRow(fila);
                    System.out.println("Dados do Livro");
                    System.out.println("IdLivro:"+ IdLivro);
                    System.out.println("Livro:"+ Nome);
                    System.out.println("Autor:"+ Autor);
                    System.out.println("Paginas:"+ Paginas);
                    System.out.println("Status:"+ Status);
                    System.out.println("--------------------");// Adiciona uma nova linha com os dados do livro ao modelo de tabela
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectar();
        }
        return modelo; // Retorna o modelo preenchido com os dados dos livros
    }
    

    public void insertarLivro(String Nome, String Autor, int Paginas, String Status) throws SQLException {
        try {
            conectar();
            String consulta = "INSERT INTO Livros (Nome, Autor, Paginas, Status) VALUES ( ?, ?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            //statement.setInt(1, Livro.setIdlivro(IdLivro)); 
            statement.setString(1, Livro.setNome(Nome));
            statement.setString(2, Livro.setAutor(Autor));
            statement.setInt(3, Livro.setPaginas(Paginas));
            statement.setString(4, Livro.setStatus(Status));

            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "O Livro Incluido foi o: " + Livro.getNome());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            
                desconectar(); 
            
        }
    }

    public void atualizarLivro(String Status, int IdLivro) throws SQLException {
        try {
            conectar();
            String consulta = "UPDATE Livros SET Status = ? WHERE Idlivro = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, Livro.setStatus(Status));
            statement.setInt(2, Livro.getIdlivro());

            statement.executeUpdate();

        
            JOptionPane.showMessageDialog(null, "O livro atualizado foi o: " + Livro.getNome());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectar(); 
        }
    }
    

    public void criarTabelaLivros() throws SQLException {
        conectar();
        String query = "CREATE TABLE IF NOT EXISTS Livros ("
                     + "IdLivro SERIAL PRIMARY KEY,"
                     + "Nome VARCHAR(255),"
                     + "Autor VARCHAR(255),"
                     + "Paginas INT,"
                     + "Status VARCHAR(50)"
                     + ");";
        executarAtualizacaoSQL(query);

        desconectar();
    }
}
