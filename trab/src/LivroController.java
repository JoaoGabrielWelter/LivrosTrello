import java.util.List;

public class LivroController {
    private LivroDAO livroDAO;

    public LivroController() {
        this.livroDAO = new LivroDAOImpl(); 
    }

    public void adicionarLivro(Livro livro) {
        livroDAO.adicionarLivro(livro);
    }

    public void atualizarLivro(Livro livro) {
        livroDAO.atualizarLivro(livro);
    }

    public void deletarLivro(int livroId) {
        livroDAO.deletarLivro(livroId);
    }

    public List<Livro> listarTodosLivros() {
        return livroDAO.listarTodosLivros();
    }

    public Livro buscarLivroPorId(int livroId) {
        return livroDAO.buscarLivroPorId(livroId);
    }
}
