package trabalhoFinal.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.*;

import trabalhoFinal.AnimalSelvagem;

/**
 * Tela de situação do animal (opções 5 a 8 do menu de console). Chama os métodos
 * polimórficos de AnimalSelvagem sem saber o tipo concreto.
 */
public class PainelSituacao extends JPanel {

    private final RepositorioAnimais repo;
    private final Runnable aoAlterar;
    private final JTextField campoId = new JTextField();
    private final JTextArea saida = new JTextArea();
    private final JButton vacinar = new JButton("Marcar como vacinado");
    private AnimalSelvagem atual;

    public PainelSituacao(RepositorioAnimais repo, Runnable aoAlterar) {
        this.repo = repo;
        this.aoAlterar = aoAlterar;
        setLayout(new BorderLayout(0, 12));
        setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        JButton consultar = new JButton("Consultar situação");
        consultar.addActionListener(e -> consultar());
        JPanel topo = new JPanel(new GridLayout(1, 3, 8, 8));
        topo.add(new JLabel("ID do animal:")); topo.add(campoId); topo.add(consultar);

        saida.setEditable(false);
        saida.setLineWrap(true);
        saida.setWrapStyleWord(true);

        vacinar.setEnabled(false);
        vacinar.addActionListener(e -> vacinar());
        JPanel rodape = new JPanel();
        rodape.add(vacinar);

        add(topo, BorderLayout.NORTH);
        add(new JScrollPane(saida), BorderLayout.CENTER);
        add(rodape, BorderLayout.SOUTH);
    }

    private void consultar() {
        try {
            atual = repo.buscarPorId(Integer.parseInt(campoId.getText().trim()));
        } catch (NumberFormatException e) {
            atual = null;
            saida.setText("ID deve ser um número inteiro.");
            vacinar.setEnabled(false);
            return;
        }
        if (atual == null) {
            saida.setText("Animal não encontrado.");
            vacinar.setEnabled(false);
            return;
        }
        mostrar();
    }

    private void mostrar() {
        saida.setText("Animal: " + atual.getNome() + " (ID: " + atual.getId() + ")\n\n"
                + "Dieta específica:\n" + atual.getDietaEspecifica() + "\n\n"
                + "Situação vacinal:\n" + atual.situacaoVacinas() + "\n\n"
                + "Situação dos ferimentos:\n" + atual.situacaoFerimentos() + "\n\n"
                + "Situação da realocação:\n" + atual.situacaoRealocacao());
        vacinar.setEnabled(!atual.isVacinado());
    }

    private void vacinar() {
        try {
            atual.setVacinado();
            repo.salvar();
            aoAlterar.run();
            mostrar();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Não foi possível gravar o arquivo: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
