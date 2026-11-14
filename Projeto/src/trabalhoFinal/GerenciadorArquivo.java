package trabalhoFinal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GerenciadorArquivo {

    public static void criarSalvarArquivo(String nomeArquivo, String conteudo) throws IOException {
        if (!nomeArquivo.endsWith(".txt") && !nomeArquivo.endsWith(".csv")) {
            nomeArquivo += ".txt";
        }
        try (BufferedWriter arquivo = new BufferedWriter(new FileWriter(nomeArquivo))) {
            arquivo.write(conteudo);
            arquivo.newLine();
        }
    }

    public static String lerArquivo(String nomeArquivo) throws IOException {
        if (nomeArquivo == null || nomeArquivo.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do arquivo não pode estar vazio.");
        }
        StringBuilder conteudo = new StringBuilder();
        try (BufferedReader arquivo = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = arquivo.readLine()) != null) {
                conteudo.append(linha).append("\n");
            }
        }
        return conteudo.toString();
    }
}
