package br.com.alura.screenmatch.exercicios.desafio2;

import java.util.Arrays;
import java.util.List;

public class ConverteMaiuscula {
    public static void main(String[] args) {
        List<String> palavras = Arrays.asList("java", "stream", "lambda");

        System.out.println("Tudo em Maiúscula: ");
        palavras.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);

        System.out.println("\nRemovendo Duplicatas:");
        List<String> frutas = Arrays.asList("apple", "banana", "apple", "orange", "banana");
        frutas.stream()
                .distinct().forEach(System.out::println);
    }
}
