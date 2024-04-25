import java.util.ArrayList;
import java.util.List;

public abstract class Biblioteca implements Cadastro, Emprestar {
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
}



