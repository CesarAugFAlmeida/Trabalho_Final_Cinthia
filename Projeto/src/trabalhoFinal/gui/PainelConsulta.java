package trabalhoFinal.gui;

import java.awt.BorderLayout;
import java.io.IOException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import trabalhoFinal.AnimalSelvagem;

/** Tela de consulta (tabela) com remoção de animal (opções 2 e 4 do menu de console). */
public class PainelConsulta extends JPanel {

    private final RepositorioAnimais repo;
    private final DefaultTableModel modelo = new DefaultTableModel(
            new String[]{"ID", "Nome", "Tipo", "Raça", "Peso (kg)", "Estágio", "Ferimentos", "Específico", "Vacinado"}, 0) {
        @Override
        public boolean isCellEditable(int r, int c) {
            return false;
        }
    };
    private final JTable tabela = new JTable(modelo);

    public PainelConsulta(RepositorioAnimais repo) {
        this.repo = repo;
        setLayout(new BorderLayout(0, 8));
        setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        JButton remover = new JButton("Remover selecionado");
        remover.addActionListener(e -> remover());
        JPanel rodape = new JPanel();
        rodape.add(remover);
        add(rodape, BorderLayout.SOUTH);
        atualizar();
    }

    public final void atualizar() {
        modelo.setRowCount(0);
        for (AnimalSelvagem a : repo.listar()) {
            modelo.addRow(new Object[]{a.getId(), a.getNome(), RepositorioAnimais.tipoDe(a), a.getRaca(),
                    a.getPeso(), a.getEstagioDesenvolvimento(), a.getFerimentos(),
                    RepositorioAnimais.extraDe(a), a.isVacinado() ? "Sim" : "Não"});
        }
    }

    private void remover() {
        int linha = tabela.getSelectedRow();
        if (linha < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um animal na tabela.");
            return;
        }
        int id = (Integer) modelo.getValueAt(linha, 0);
        if (JOptionPane.showConfirmDialog(this, "Remover o animal de ID " + id + "?", "Confirmar",
                JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            repo.remover(repo.buscarPorId(id));
            atualizar();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Não foi possível gravar o arquivo: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
