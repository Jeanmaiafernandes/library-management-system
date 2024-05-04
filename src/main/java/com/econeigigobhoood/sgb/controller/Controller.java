package com.econeigigobhoood.sgb.controller;

import java.util.ArrayList;
import java.util.List;

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
       
    // ====================================
    // ========= DATABASE CONFIG ==========
    // ====================================
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
    public void desconectar() {
        try{
            if (conexion != null) {
            conexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public DefaultTableModel table() throws SQLException {
        //DefaultTableModel modelo = new DefaultTableModel(); // Inicializa o modelo de tabela
    
        try {
            conectar();
            ResultSet resultado = executarSQL("SELECT * FROM Livros;"); // Consulta SQL para selecionar todos os livros
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
    
    // ===============================================
    // ========= OPERAÇÕES NO ESTOQUE GERAL ==========
    // ===============================================
    //Conexão de MainMenu para Controller
    // Método de cadastro de livro no banco de dados
    public void insertarLivro(Livro entidade) throws SQLException {
        try {
            conectar();
            String consulta = "INSERT INTO Livros (Nome, Autor, Paginas, Status) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, entidade.getNome());
            statement.setString(2, entidade.getAutor());
            statement.setInt(3, entidade.getPaginas());
            statement.setString(4, "Em estoque");

            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "O Livro Incluido foi o: " + entidade.getNome());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectar(); 
        }
    }

    public void excluiLivro(int id) {
        String query = "DELETE FROM Livros WHERE id = ?";
        
        try {
            conectar();
            PreparedStatement stmt = conexion.prepareStatement(query);
            stmt.setInt(1, id);

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectar(); 
        }
    }

    public Livro buscaLivro(int id) {
        String query = "SELECT * FROM Livros WHERE id = ?";
        
        try {
            conectar();
            PreparedStatement stmt = conexion.prepareStatement(query);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("Nome");
                String autor = rs.getString("Autor");
                int paginas = rs.getInt("Paginas");
                String status = rs.getString("Status");

                Livro livro = new Livro(id, nome, autor, paginas, status);
                
                return livro;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectar(); 
        }
        return null;
    }

    public void atualizarLivro(Livro entidade) {
        String query = "UPDATE Livros SET Nome = ?, Autor = ?, Paginas = ? WHERE id = ?";

        try {
            conectar();
            PreparedStatement stmt = conexion.prepareStatement(query);
            stmt.setString(1, entidade.getNome());
            stmt.setString(2, entidade.getAutor());
            stmt.setInt(3, entidade.getPaginas());
            stmt.setInt(4, entidade.getIdlivro());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectar();
        }
    }

    public List<Livro> listaLivros() {
        List<Livro> livros = new ArrayList<Livro>();
        String query = "SELECT * FROM Livros";

        try {
            conectar();
            PreparedStatement stmt = conexion.prepareStatement(query);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("IdLivro");
                String nome = rs.getString("Nome");
                String autor = rs.getString("Autor");
                int paginas = rs.getInt("Paginas");
                String status = rs.getString("Status");

                Livro livro = new Livro (id, nome, autor, paginas, status);

                livros.add (livro);
            }

            return livros;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectar();
        }
        return null;
    }

    // ============================================
    // ========= OPERAÇÕES EM EMPRESTADO ==========
    // ============================================
    public void emprestaLivro(int id) {
        String query = "UPDATE Livros SET Status = ? WHERE Idlivro = ?";
        
        try {
            conectar();
            PreparedStatement statement = conexion.prepareStatement(query);

            statement.setString(1, "Emprestado");
            statement.setInt(2, id);

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectar(); 
        }
    }

    public void devolveLivro(int id) {
        String query = "UPDATE Livros SET Status = ? WHERE Idlivro = ?";
        
        try {
            conectar();
            PreparedStatement statement = conexion.prepareStatement(query);

            statement.setString(1, "Em estoque");
            statement.setInt(2, id);

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectar(); 
        }
    }

    public List<Livro> listaLivrosEmprestado() {
        List<Livro> livros = new ArrayList<Livro>();
        String query = "SELECT * FROM Livros WHERE Status = ?";

        try {
            conectar();
            PreparedStatement stmt = conexion.prepareStatement(query);
            stmt.setString(1, "Emprestado");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("IdLivro");
                String nome = rs.getString("Nome");
                String autor = rs.getString("Autor");
                int paginas = rs.getInt("Paginas");
                String status = rs.getString("Status");

                Livro livro = new Livro (id, nome, autor, paginas, status);

                livros.add (livro);
            }

            return livros;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectar();
        }
        return null;
    }

    // ===================================
    // ========= TABLE CREATION ==========
    // ===================================
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
