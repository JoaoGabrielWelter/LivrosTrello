import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LivroViewSwing {
    private LivroController livroController;
    private JFrame frame;
    private JTextArea textAreaLivros;

    public LivroViewSwing() {
        this.livroController = new LivroController();
        criarJanela();
    }

    private void criarJanela() {
        frame = new JFrame("CRUD de Livros");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel painel = new JPanel(new BorderLayout());

        JButton btnCadastrar = new JButton("Cadastrar Livro");
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cadastrarLivro();
            }
        });

        JButton btnEditar = new JButton("Editar Livro");
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editarLivro();
            }
        });

        JButton btnExcluir = new JButton("Excluir Livro");
        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                excluirLivro();
            }
        });

        textAreaLivros = new JTextArea(15, 30);
        JScrollPane scrollPane = new JScrollPane(textAreaLivros);
        textAreaLivros.setEditable(false);

        JPanel botoesPanel = new JPanel();
        botoesPanel.add(btnCadastrar);
        botoesPanel.add(btnEditar); //
        botoesPanel.add(btnExcluir);

        painel.add(botoesPanel, BorderLayout.NORTH);
        painel.add(scrollPane, BorderLayout.CENTER);

        frame.getContentPane().add(painel);
        frame.setVisible(true);
    }

    private void cadastrarLivro() {
        String titulo = JOptionPane.showInputDialog(frame, "Digite o título do livro:");
        String autor = JOptionPane.showInputDialog(frame, "Digite o nome do autor:");
        String anoStr = JOptionPane.showInputDialog(frame, "Digite o ano do livro:");
        String descricao = JOptionPane.showInputDialog(frame, "Digite a descrição do livro:");

        try {
            int ano = Integer.parseInt(anoStr);
            Livro livro = new Livro();
            livro.setTitulo(titulo);
            livro.setAutor(autor);
            livro.setAno(ano);
            livro.setDescricao(descricao);

            livroController.adicionarLivro(livro);
            JOptionPane.showMessageDialog(frame, "Livro cadastrado com sucesso!");
            listarLivros(); // Atualiza a lista após cadastrar o livro
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Algo deu errado");
        }
    }

    private void listarLivros() {
        List<Livro> livros = livroController.listarTodosLivros();
        StringBuilder texto = new StringBuilder();
        for (Livro livro : livros) {
            texto.append(livro.getId()).append(": ").append(livro.getTitulo()).append(" por ")
                    .append(livro.getAutor()).append(" (").append(livro.getAno()).append(") - ")
                    .append(livro.getDescricao()).append("\n");
        }
        textAreaLivros.setText(texto.toString());
    }

    private void editarLivro() {
        String idStr = JOptionPane.showInputDialog(frame, "Digite o ID do livro que deseja editar:");

        try {
            int id = Integer.parseInt(idStr);
            Livro livro = livroController.buscarLivroPorId(id);
            if (livro != null) {
                String titulo = JOptionPane.showInputDialog(frame, "Novo título do livro:", livro.getTitulo());
                String autor = JOptionPane.showInputDialog(frame, "Novo autor do livro:", livro.getAutor());
                String anoStr = JOptionPane.showInputDialog(frame, "Novo ano do livro:", livro.getAno());
                String descricao = JOptionPane.showInputDialog(frame, "Nova descrição do livro:", livro.getDescricao());

                int novoAno = Integer.parseInt(anoStr);

                livro.setTitulo(titulo);
                livro.setAutor(autor);
                livro.setAno(novoAno);
                livro.setDescricao(descricao);

                livroController.atualizarLivro(livro);
                JOptionPane.showMessageDialog(frame, "Livro editado com sucesso!");
                listarLivros(); // Atualiza a lista após editar o livro
            } else {
                JOptionPane.showMessageDialog(frame, "Livro não encontrado!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Por favor, digite um ID ou ano válidos.");
        }
    }

    private void excluirLivro() {
        String idStr = JOptionPane.showInputDialog(frame, "Digite o ID do livro que deseja excluir:");

        try {
            int id = Integer.parseInt(idStr);
            Livro livro = livroController.buscarLivroPorId(id);
            if (livro != null) {
                livroController.deletarLivro(id);
                JOptionPane.showMessageDialog(frame, "Livro excluído com sucesso!");
                listarLivros(); // Atualiza a lista após excluir o livro
            } else {
                JOptionPane.showMessageDialog(frame, "Livro não encontrado!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Por favor, digite um ID válido.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LivroViewSwing();
            }
        });
    }
}
