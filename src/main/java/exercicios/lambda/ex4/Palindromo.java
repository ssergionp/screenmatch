package exercicios.lambda.ex4;

public class Palindromo {
    public static void main(String[] args) {
        VerificaPalindromo palavra = texto ->
                texto.equals(new StringBuilder(texto).reverse().toString());
        System.out.println(palavra.verificarPalindromo("ana"));
        System.out.println(palavra.verificarPalindromo("abacate"));
        System.out.println(palavra.verificarPalindromo("arara"));
        System.out.println(palavra.verificarPalindromo("radar"));
    }
}
