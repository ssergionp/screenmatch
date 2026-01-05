package exercicios.lambda.ex1;

public class ExpressãoLambda {
    public static void main(String[] args) {
        Multiplicacao multiplicacao = (a, b) -> a * b;
        System.out.println(multiplicacao.multiplicacao(2, 3));
    }
}
