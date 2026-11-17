package trabalhoFinal.gui;

import java.io.IOException;
import javax.swing.*;

/** Janela principal: uma aba por tela do sistema. Ponto de entrada da interface gráfica. */
public class JanelaPrincipal extends JFrame {

    public JanelaPrincipal(RepositorioAnimais repo) {
        super("Centro de Reabilitação de Animais Selvagens");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        PainelConsulta consulta = new PainelConsulta(repo);
        Runnable aoAlterar = consulta::atualizar; // recarrega a tabela após qualquer alteração

        JTabbedPane abas = new JTabbedPane();
        abas.addTab("Cadastrar", new PainelCadastro(repo, aoAlterar));
        abas.addTab("Consultar / Remover", consulta);
        abas.addTab("Atualizar", new PainelAtualizacao(repo, aoAlterar));
        abas.addTab("Situação do animal", new PainelSituacao(repo, aoAlterar));
        add(abas);

        setSize(760, 480);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new JanelaPrincipal(new RepositorioAnimais()).setVisible(true);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erro ao ler animais.csv: " + e.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
