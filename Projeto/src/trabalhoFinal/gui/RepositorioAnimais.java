package trabalhoFinal.gui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import trabalhoFinal.AnimalSelvagem;
import trabalhoFinal.Ave;
import trabalhoFinal.GerenciadorArquivo;
import trabalhoFinal.Mamifero;
import trabalhoFinal.Reptil;

/**
 * Guarda a coleção de animais usada pelas telas e persiste em CSV usando o
 * GerenciadorArquivo existente. Não altera nenhuma classe do modelo.
 * Formato por linha: tipo;id;nome;peso;ferimentos;raca;estagio;extra;vacinado
 */
public class RepositorioAnimais {

    private static final String ARQUIVO = "animais.csv";
    private final List<AnimalSelvagem> animais = new ArrayList<>();

    public RepositorioAnimais() throws IOException {
        carregar();
    }

    public List<AnimalSelvagem> listar() {
        return animais;
    }

    public AnimalSelvagem buscarPorId(int id) {
        for (AnimalSelvagem a : animais) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }

    /** @throws IllegalArgumentException se o ID já existir. */
    public void adicionar(AnimalSelvagem animal) throws IOException {
        if (buscarPorId(animal.getId()) != null) {
            throw new IllegalArgumentException("Já existe um animal com o ID " + animal.getId() + ".");
        }
        animais.add(animal);
        salvar();
    }

    public void remover(AnimalSelvagem animal) throws IOException {
        animais.remove(animal);
        salvar();
    }

    /** Deve ser chamado após qualquer alteração feita diretamente em um animal. */
    public void salvar() throws IOException {
        StringBuilder sb = new StringBuilder();
        for (AnimalSelvagem a : animais) {
            sb.append(paraLinha(a)).append("\n");
        }
        GerenciadorArquivo.criarSalvarArquivo(ARQUIVO, sb.toString().trim());
    }

    /** Tipo legível do animal (Ave, Mamífero, Réptil). */
    public static String tipoDe(AnimalSelvagem a) {
        if (a instanceof Ave) return "Ave";
        if (a instanceof Mamifero) return "Mamífero";
        return "Réptil";
    }

    /** Atributo específico de cada subclasse (porte ou alimentação). */
    public static String extraDe(AnimalSelvagem a) {
        if (a instanceof Ave) return ((Ave) a).getPorte();
        if (a instanceof Mamifero) return ((Mamifero) a).getAlimentacao();
        return ((Reptil) a).getAlimentacao();
    }

    private void carregar() throws IOException {
        if (!new File(ARQUIVO).exists()) {
            return;
        }
        for (String linha : GerenciadorArquivo.lerArquivo(ARQUIVO).split("\n")) {
            if (linha.isBlank()) continue;
            String[] c = linha.split(";", -1);
            if (c.length < 9) continue; // linha corrompida: ignora
            try {
                int id = Integer.parseInt(c[1]);
                double peso = Double.parseDouble(c[3]);
                AnimalSelvagem a;
                switch (c[0]) {
                    case "Ave": a = new Ave(id, c[2], peso, c[4], c[5], c[6], c[7]); break;
                    case "Mamífero": a = new Mamifero(id, c[2], peso, c[4], c[5], c[6], c[7]); break;
                    default: a = new Reptil(id, c[2], peso, c[4], c[5], c[6], c[7]);
                }
                if (Boolean.parseBoolean(c[8])) a.setVacinado();
                animais.add(a);
            } catch (NumberFormatException e) {
                // linha com número inválido: ignora
            }
        }
    }

    private static String paraLinha(AnimalSelvagem a) {
        return String.join(";", tipoDe(a), String.valueOf(a.getId()), limpar(a.getNome()),
                String.valueOf(a.getPeso()), a.getFerimentos(), limpar(a.getRaca()),
                a.getEstagioDesenvolvimento(), extraDe(a), String.valueOf(a.isVacinado()));
    }

    private static String limpar(String s) {
        return s.replace(';', ',').replace('\n', ' ');
    }
}
