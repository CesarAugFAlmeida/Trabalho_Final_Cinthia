package trabalhoFinal.gui;

import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.*;

import trabalhoFinal.AnimalSelvagem;
import trabalhoFinal.Ave;
import trabalhoFinal.Mamifero;
import trabalhoFinal.Reptil;

/** Tela de cadastro de novo animal (opção 1 do menu de console). */
public class PainelCadastro extends JPanel {

    static final String[] FERIMENTOS = {"Grave", "Moderado", "Leve", "Cicatrizado"};
    static final String[] ESTAGIOS = {"Filhote", "Subadulto", "Adulto", "Geriátrico"};
    private static final String[] TIPOS = {"Ave", "Mamífero", "Réptil"};
    // Textos exatamente como Ave.getDietaEspecifica() os compara (o Main usa textos diferentes).
    private static final String[] PORTES = {"Muito pequeno porte", "Pequeno porte", "Médio porte", "Grande porte", "Porte gigante", "Ave de Rapina"};
    private static final String[] ALIMENTACOES = {"Herbívoro", "Carnívoro", "Onívoro"};
    private static final String[] ALIMENTACOES_REPTIL = {"Herbívoro", "Carnívoro", "Onívoro", "Insetívoro"};

    private final RepositorioAnimais repo;
    private final Runnable aoAlterar;

    private final JTextField campoId = new JTextField();
    private final JTextField campoNome = new JTextField();
    private final JTextField campoPeso = new JTextField();
    private final JTextField campoRaca = new JTextField();
    private final JComboBox<String> comboFerimentos = new JComboBox<>(FERIMENTOS);
    private final JComboBox<String> comboEstagio = new JComboBox<>(ESTAGIOS);
    private final JComboBox<String> comboTipo = new JComboBox<>(TIPOS);
    private final JLabel rotuloExtra = new JLabel("Porte:");
    private final JComboBox<String> comboExtra = new JComboBox<>(PORTES);

    public PainelCadastro(RepositorioAnimais repo, Runnable aoAlterar) {
        this.repo = repo;
        this.aoAlterar = aoAlterar;
        setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        JPanel form = new JPanel(new GridLayout(0, 2, 8, 8));
        form.add(new JLabel("ID:")); form.add(campoId);
        form.add(new JLabel("Nome:")); form.add(campoNome);
        form.add(new JLabel("Peso (kg):")); form.add(campoPeso);
        form.add(new JLabel("Raça:")); form.add(campoRaca);
        form.add(new JLabel("Ferimentos:")); form.add(comboFerimentos);
        form.add(new JLabel("Estágio de desenvolvimento:")); form.add(comboEstagio);
        form.add(new JLabel("Tipo:")); form.add(comboTipo);
        form.add(rotuloExtra); form.add(comboExtra);

        comboTipo.addActionListener(e -> atualizarCampoExtra());

        JButton salvar = new JButton("Cadastrar");
        salvar.addActionListener(e -> cadastrar());

        setLayout(new java.awt.BorderLayout(0, 12));
        add(form, java.awt.BorderLayout.NORTH);
        JPanel rodape = new JPanel();
        rodape.add(salvar);
        add(rodape, java.awt.BorderLayout.CENTER);
    }

    /** O campo extra muda conforme o tipo: porte (Ave) ou alimentação. */
    private void atualizarCampoExtra() {
        String tipo = (String) comboTipo.getSelectedItem();
        if ("Ave".equals(tipo)) {
            rotuloExtra.setText("Porte:");
            comboExtra.setModel(new DefaultComboBoxModel<>(PORTES));
        } else {
            rotuloExtra.setText("Alimentação:");
            comboExtra.setModel(new DefaultComboBoxModel<>("Réptil".equals(tipo) ? ALIMENTACOES_REPTIL : ALIMENTACOES));
        }
    }

    private void cadastrar() {
        try {
            int id = Integer.parseInt(campoId.getText().trim());
            double peso = Double.parseDouble(campoPeso.getText().trim().replace(',', '.'));
            String nome = campoNome.getText().trim();
            String raca = campoRaca.getText().trim();
            if (nome.isEmpty() || raca.isEmpty()) {
                throw new IllegalArgumentException("Preencha nome e raça.");
            }
            if (peso <= 0) {
                throw new IllegalArgumentException("O peso deve ser maior que zero.");
            }
            String ferimentos = (String) comboFerimentos.getSelectedItem();
            String estagio = (String) comboEstagio.getSelectedItem();
            String extra = (String) comboExtra.getSelectedItem();

            AnimalSelvagem animal;
            switch ((String) comboTipo.getSelectedItem()) {
                case "Ave": animal = new Ave(id, nome, peso, ferimentos, raca, estagio, extra); break;
                case "Mamífero": animal = new Mamifero(id, nome, peso, ferimentos, raca, estagio, extra); break;
                default: animal = new Reptil(id, nome, peso, ferimentos, raca, estagio, extra);
            }
            repo.adicionar(animal);
            aoAlterar.run();
            limpar();
            JOptionPane.showMessageDialog(this, "Animal cadastrado com sucesso!");
        } catch (NumberFormatException e) {
            erro("ID deve ser inteiro e peso deve ser um número.");
        } catch (IllegalArgumentException e) {
            erro(e.getMessage());
        } catch (IOException e) {
            erro("Não foi possível gravar o arquivo: " + e.getMessage());
        }
    }

    private void limpar() {
        campoId.setText(""); campoNome.setText(""); campoPeso.setText(""); campoRaca.setText("");
    }

    private void erro(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
