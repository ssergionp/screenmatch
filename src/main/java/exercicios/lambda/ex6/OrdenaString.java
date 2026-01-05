package exercicios.lambda.ex6;

import java.util.Arrays;
import java.util.List;

public class OrdenaString {
    public static void main(String[] args) {
        List<String> palavras = Arrays.asList("ana", "boina", "varanda", "arara", "barco", "abacate", "radar");
        palavras.sort((a, b) -> a.compareTo(b));
        System.out.println(palavras);
    }
}
