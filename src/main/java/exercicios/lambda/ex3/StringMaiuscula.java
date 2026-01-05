package exercicios.lambda.ex3;

public class StringMaiuscula {
    public static void main(String[] args) {
        ConverteMaiuscula palavra = texto -> texto.toUpperCase();
        System.out.println(palavra.converter("Meu Texto com palavras aleatorias!"));
    }
}
