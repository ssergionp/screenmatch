package br.com.alura.screenmatch.exercicios.desafio2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OrdenaSublistas {
    public static void main(String[] args) {
        List<List<Integer>> listaDeNumeros = Arrays.asList(
                Arrays.asList(1, 2, 3, 4),
                Arrays.asList(5, 6, 7, 8),
                Arrays.asList(9, 10, 11, 12),
                Arrays.asList(13, 14, 15, 16)
        );

        //código para filtrar e ordenar números primos
        List<Integer> numerosPrimos = listaDeNumeros.stream()
                .flatMap(List::stream)
                .filter(OrdenaSublistas::isPrimo)
                .sorted()
                .collect(Collectors.toList()); // Coletar em uma lista

        System.out.println("Números primos ordenados: " + numerosPrimos);
    }

    private static boolean isPrimo(int numero) {
        if (numero < 2) return false; // Números menores que 2 não são primos

        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false; // Divisível por outro número que não 1 e ele mesmo
            }
        }

        return true;
    }
}
