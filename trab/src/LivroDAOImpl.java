import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LivroDAOImpl implements LivroDAO {
    private Map<Integer, Livro> mapaLivros = new HashMap<>();
    private int contadorId = 1;

    @Override
    public void adicionarLivro(Livro livro) {
        livro.setId(contadorId);
        mapaLivros.put(contadorId, livro);
        contadorId++;
    }

    @Override
    public void atualizarLivro(Livro livro) {
        mapaLivros.put(livro.getId(), livro);
    }

    @Override
    public void deletarLivro(int livroId) {
        mapaLivros.remove(livroId);
    }

    @Override
    public List<Livro> listarTodosLivros() {
        return new ArrayList<>(mapaLivros.values());
    }

    @Override
    public Livro buscarLivroPorId(int livroId) {
        return mapaLivros.get(livroId);
    }
}
