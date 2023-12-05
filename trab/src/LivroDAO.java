import java.util.List;

public interface LivroDAO {
    void adicionarLivro(Livro livro);
    void atualizarLivro(Livro livro);
    void deletarLivro(int livroId);
    List<Livro> listarTodosLivros();
    Livro buscarLivroPorId(int livroId);
}
