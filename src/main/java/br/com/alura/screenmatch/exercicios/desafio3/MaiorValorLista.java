package br.com.alura.screenmatch.exercicios.desafio3;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MaiorValorLista {
    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(10, 20, 30, 40, 50);

        Optional<Integer> max = numeros.stream()
                .max(Integer::compare);
        max.ifPresent(System.out::println);
    }
}
