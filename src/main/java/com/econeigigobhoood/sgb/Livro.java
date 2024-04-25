import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Livro extends Biblioteca {
    
    // Conecta com o banco de Dados
    private String url = "jdbc:mysql://localhost:3306/nome_do_banco";
    private String usuario = "usuario";
    private String senha = "senha";

    private int Idlivro;
    private String Nome;
    private String Autor;
    private int Paginas;

    public Livro(int Idlivro, String Nome, String Autor, int Paginas) {
        this.Idlivro = Idlivro;
        this.Nome = Nome;
        this.Autor = Autor;
        this.Paginas = Paginas;
    }

    // Getters
    public int getIdlivro() {
        return Idlivro;
    }

    public String getNome() {
        return Nome;
    }

    public String getAutor() {
        return Autor;
    }

    public int getPaginas() {
        return Paginas;
    }

    // Setters
    public void setIdlivro(int Idlivro) {
        this.Idlivro = Idlivro;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public void setAutor(String Autor) {
        this.Autor = Autor;
    }

    public void setPaginas(int Paginas) {
        this.Paginas = Paginas;
    }

    // Inserir Livros 
    public boolean adicionarLivro() {
        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
            // SQL para inserção
            String sql = "INSERT INTO Livro (IdLivro, Nome, Autor, Paginas) VALUES (?, ?, ?, ?)";

            try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
                pstmt.setInt(1, Idlivro);
                pstmt.setString(2, Nome);
                pstmt.setString(3, Autor);
                pstmt.setInt(4, Paginas);

                pstmt.executeUpdate();

                System.out.println("Dados inseridos com sucesso!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados: " + e.getMessage());
            return false;
        }
    }

    // Listar Livros 
    public void listarLivros() {
        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
            String SQL = "SELECT IdLivro, Nome, Autor, Paginas FROM Livro";

            try (PreparedStatement pstmt = conexao.prepareStatement(SQL)) {
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        int IdLivro = rs.getInt("IdLivro");
                        String Nome = rs.getString("Nome");
                        String Autor = rs.getString("Autor");
                        int Paginas = rs.getInt("Paginas");

                        System.out.println("IdLivro: " + IdLivro);
                        System.out.println("Nome: " + Nome);
                        System.out.println("Autor: " + Autor);
                        System.out.println("Paginas: " + Paginas);
                        System.out.println("-------------------------");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar livros: " + e.getMessage());
        }
    }

    // Emprestar
    public void emprestar(String Status, int IdLivro) {
        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
            // SQL para atualização
            String sql = "UPDATE Livro SET Status = ? WHERE IdLivro = ?";

            try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
                pstmt.setString(1, Status);
                pstmt.setInt(2, IdLivro);

                pstmt.executeUpdate();

                System.out.println("Dados alterados com sucesso!");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao emprestar livro: " + e.getMessage());
        }
    }

    // Devolver
    public void devolver(String Status, int IdLivro) {
        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
            // SQL para atualização
            String sql = "UPDATE Livro SET Status = ? WHERE IdLivro = ?";

            try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
                pstmt.setString(1, Status);
                pstmt.setInt(2, IdLivro);

                pstmt.executeUpdate();

                System.out.println("Dados alterados com sucesso!");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao devolver livro: " + e.getMessage());
        }
    }
}
