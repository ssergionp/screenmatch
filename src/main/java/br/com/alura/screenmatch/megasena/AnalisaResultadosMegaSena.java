package br.com.alura.screenmatch.megasena;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class AnalisaResultadosMegaSena {
    public static void main(String[] args) {
        String caminhoArquivo =
                "H:\\Alura\\Java\\Back End Java\\screenmatch\\planilhas\\todos_resultados_mega_sena.xlsx";
        List<List<Integer>> todosSorteios = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(caminhoArquivo);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            // i=1 pula o cabeçalho (Concurso, Data, bola 1...)
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                List<Integer> sorteio = new ArrayList<>();
                // Colunas 2 a 7 contêm as bolas (index 0,1 são Concurso e Data)
                for (int j = 2; j <= 7; j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                        sorteio.add((int) cell.getNumericCellValue());
                    }
                }

                if (sorteio.size() == 6) {
                    // Ordenamos para identificar sequências numéricas corretamente
                    Collections.sort(sorteio);
                    todosSorteios.add(sorteio);
                }
            }

            processarEstatisticas(todosSorteios);

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo Excel: " + e.getMessage());
        }
    }

    private static void processarEstatisticas(List<List<Integer>> sorteios) {
        Map<Integer, Integer> frequencia = new HashMap<>();
        Map<String, Integer> sequencias = new HashMap<>();
        List<Double> mediasPorJogo = new ArrayList<>();

        for (List<Integer> s : sorteios) {
            double soma = 0;
            for (int i = 0; i < s.size(); i++) {
                int num = s.get(i);
                soma += num;
                frequencia.put(num, frequencia.getOrDefault(num, 0) + 1);

                // Verifica números consecutivos no mesmo sorteio
                if (i < s.size() - 1 && s.get(i + 1) == num + 1) {
                    String seq = num + " e " + (num + 1);
                    sequencias.put(seq, sequencias.getOrDefault(seq, 0) + 1);
                }
            }
            mediasPorJogo.add(soma / 6.0);
        }

        // --- 1. RANKINGS ---
        List<Map.Entry<Integer, Integer>> listaFreq = new ArrayList<>(frequencia.entrySet());
        listaFreq.sort(Map.Entry.comparingByValue());

        System.out.println("=== 10 NÚMEROS QUE MAIS SAÍRAM ===");
        List<Integer> maisSairam = listaFreq.stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(10).peek(e -> System.out.println("Nº " + e.getKey() + ": " + e.getValue() + " vezes"))
                .map(Map.Entry::getKey).toList();

        System.out.println("\n=== 10 NÚMEROS QUE MENOS SAÍRAM ===");
        listaFreq.stream().limit(10).forEach(e -> System.out.println("Nº " + e.getKey() + ": " + e.getValue() + " vezes"));

        // --- 2. SEQUÊNCIAS ---
        System.out.println("\n=== SEQUÊNCIAS QUE MAIS SAÍRAM ===");
        sequencias.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(5)
                .forEach(e -> System.out.println("A dupla " + e.getKey() + " apareceu " + e.getValue() + " vezes"));

        // --- 3. MÉTRICAS ESTATÍSTICAS ---
        double mediaGeral = mediasPorJogo.stream().mapToDouble(d -> d).average().orElse(0);
        double desvioPadrao = Math.sqrt(mediasPorJogo.stream()
                .mapToDouble(m -> Math.pow(m - mediaGeral, 2)).average().orElse(0));

        System.out.printf("\n=== MÉTRICAS GERAIS ===\n");
        System.out.printf("Média de equilíbrio dos sorteios: %.2f (Teórica: 30.5)\n", mediaGeral);
        System.out.printf("Desvio Padrão das médias: %.2f\n", desvioPadrao);

        // --- 4. SUGESTÕES DE JOGOS ---
        gerarJogos(maisSairam);
    }

    private static void gerarJogos(List<Integer> quentes) {
        System.out.println("\n=== SUGESTÕES DE JOGOS (3 POR CATEGORIA) ===");
        Random r = new Random();
        int[] tamanhos = {6, 7, 8, 9, 10};

        for (int tam : tamanhos) {
            System.out.println("\n--- Jogos de " + tam + " dezenas ---");
            for (int i = 1; i <= 3; i++) {
                Set<Integer> jogo = new TreeSet<>();
                // Adiciona 2 números do Top 10 para aumentar a "probabilidade"
                while (jogo.size() < 2) jogo.add(quentes.get(r.nextInt(quentes.size())));

                // Preenche o restante de forma aleatória entre 1 e 60
                while (jogo.size() < tam) {
                    jogo.add(r.nextInt(60) + 1);
                }
                System.out.println("Jogo " + i + ": " + jogo);
            }
        }
    }
}