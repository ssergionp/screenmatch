package exercicios.lambda.ex2;

public class ChecaPrimo {
    public static void main(String[] args) {
        Primo primo = numero -> {
            if (numero <= 1)
                return false;
            for (int i = 2; i <= Math.sqrt(numero); i++) {
                if (numero % i == 0)
                    return false;
            }
            return true;
        };

        System.out.println(primo.verificarPrimo(5));
        System.out.println(primo.verificarPrimo(11));
        System.out.println(primo.verificarPrimo(12));
    }
}
