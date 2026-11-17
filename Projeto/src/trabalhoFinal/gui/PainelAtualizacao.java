package trabalhoFinal.gui;

import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.*;

import trabalhoFinal.AnimalSelvagem;

/** Tela de atualização de peso, ferimentos e estágio (opção 3 do menu de console). */
public class PainelAtualizacao extends JPanel {

    private final RepositorioAnimais repo;
    private final Runnable aoAlterar;

    private final JTextField campoId = new JTextField();
    private final JTextField campoPeso = new JTextField();
    private final JComboBox<String> comboFerimentos = new JComboBox<>(PainelCadastro.FERIMENTOS);
    private final JComboBox<String> comboEstagio = new JComboBox<>(PainelCadastro.ESTAGIOS);
    private AnimalSelvagem atual;
    private final JButton salvar = new JButton("Salvar alterações");

    public PainelAtualizacao(RepositorioAnimais repo, Runnable aoAlterar) {
        this.repo = repo;
        this.aoAlterar = aoAlterar;
        setLayout(new java.awt.BorderLayout(0, 12));
        setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        JButton buscar = new JButton("Buscar");
        buscar.addActionListener(e -> buscar());
        JPanel topo = new JPanel(new GridLayout(1, 3, 8, 8));
        topo.add(new JLabel("ID do animal:")); topo.add(campoId); topo.add(buscar);

        JPanel form = new JPanel(new GridLayout(0, 2, 8, 8));
        form.add(new JLabel("Peso (kg):")); form.add(campoPeso);
        form.add(new JLabel("Ferimentos:")); form.add(comboFerimentos);
        form.add(new JLabel("Estágio de desenvolvimento:")); form.add(comboEstagio);

        salvar.setEnabled(false);
        salvar.addActionListener(e -> salvar());

        JPanel centro = new JPanel(new java.awt.BorderLayout(0, 12));
        centro.add(form, java.awt.BorderLayout.NORTH);
        JPanel rodape = new JPanel();
        rodape.add(salvar);
        centro.add(rodape, java.awt.BorderLayout.CENTER);

        add(topo, java.awt.BorderLayout.NORTH);
        add(centro, java.awt.BorderLayout.CENTER);
    }

    private void buscar() {
        try {
            atual = repo.buscarPorId(Integer.parseInt(campoId.getText().trim()));
        } catch (NumberFormatException e) {
            atual = null;
            JOptionPane.showMessageDialog(this, "ID deve ser um número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        salvar.setEnabled(atual != null);
        if (atual == null) {
            JOptionPane.showMessageDialog(this, "Animal não encontrado.");
            return;
        }
        campoPeso.setText(String.valueOf(atual.getPeso()));
        comboFerimentos.setSelectedItem(atual.getFerimentos());
        comboEstagio.setSelectedItem(atual.getEstagioDesenvolvimento());
    }

    private void salvar() {
        try {
            double peso = Double.parseDouble(campoPeso.getText().trim().replace(',', '.'));
            if (peso <= 0) {
                throw new IllegalArgumentException("O peso deve ser maior que zero.");
            }
            atual.setPeso(peso);
            atual.setFerimentos((String) comboFerimentos.getSelectedItem());
            atual.setEstagioDesenvolvimento((String) comboEstagio.getSelectedItem());
            repo.salvar();
            aoAlterar.run();
            JOptionPane.showMessageDialog(this, "Cadastro atualizado com sucesso!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Peso deve ser um número.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Não foi possível gravar o arquivo: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
