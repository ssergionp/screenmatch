package br.com.alura.screenmatch.exercicios.desafio3;

import java.util.Arrays;
import java.util.List;

public class SomaDosQuadrados {
    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6);

        // código da filtragem e agrupamento dos dados
        int soma = numeros.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .reduce(0, Integer::sum);
        System.out.println(soma);
    }
}
