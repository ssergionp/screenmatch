package br.com.alura.screenmatch.exercicios.desafio2;

import java.util.Arrays;
import java.util.List;

public class Pares {
    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6);

        System.out.println("Pares: ");
        numeros.stream()
                .filter(n -> n % 2 == 0)
                .forEach(System.out::println);

        System.out.println("\nÍmpares: ");
        numeros.stream()
                .filter(n -> n % 2 != 0)
                .forEach(System.out::println);
    }
}
