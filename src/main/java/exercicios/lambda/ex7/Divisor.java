package exercicios.lambda.ex7;

@FunctionalInterface
public interface Divisor {
    int dividir(int a, int b) throws ArithmeticException;
}
