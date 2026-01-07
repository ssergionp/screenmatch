package br.com.alura.screenmatch.exercicios.desafio2;

import br.com.alura.screenmatch.model.DadosEpisodio;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PrincipalProduto {
    public static void main(String[] args) {
        List<Produto> produtos = Arrays.asList(
                new Produto("Smartphone", 800.0, "Eletrônicos"),
                new Produto("Notebook", 1500.0, "Eletrônicos"),
                new Produto("Teclado", 200.0, "Eletrônicos"),
                new Produto("Cadeira", 300.0, "Móveis"),
                new Produto("Monitor", 900.0, "Eletrônicos"),
                new Produto("Mesa", 700.0, "Móveis")
        );

        System.out.println("Produtos Filtrados:");
        List<Produto>  listaFiltrada = produtos.stream()
                .filter(p -> p.getPreco() < 1000 && p.getCategoria().equals("Eletrônicos"))
                .sorted((p1, p2) -> Double.compare(p1.getPreco(), p2.getPreco()))
                .collect(Collectors.toList());

        listaFiltrada.forEach(System.out::println);

        System.out.println("\n--- Top 3 Mais baratos ---");
        List<Produto>  listaMaisBaratos = produtos.stream()
                .filter(p -> p.getPreco() < 1000 && p.getCategoria().equals("Eletrônicos"))
                .sorted((p1, p2) -> Double.compare(p1.getPreco(), p2.getPreco()))
                .limit(3)
                .collect(Collectors.toList());

       listaMaisBaratos.forEach(System.out::println);
    }
}
