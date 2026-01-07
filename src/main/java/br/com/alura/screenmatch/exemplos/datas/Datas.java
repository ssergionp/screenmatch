package br.com.alura.screenmatch.exemplos.datas;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Datas {
    public static void main(String[] args) {
        LocalDate hoje = LocalDate.now();
        System.out.println("Hoje é: " + hoje);

        String pattern = "E, dd MM yyyy HH:mm:ss z";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        System.out.println("\nData Formatada: " + date);

        LocalDate meuAniversario = LocalDate.of(1973, Month.JANUARY, 02);
        System.out.println("\nMeu Aniverário: " + meuAniversario);

        int idade = meuAniversario.until(hoje).getYears();
        System.out.println("\nMinha Idade: " + idade + " anos.");

        Period periodo = Period.between(hoje, meuAniversario);
        System.out.println("Periodo: " + periodo);

        LocalTime horaAtual = LocalTime.now();
        System.out.println("\nHora: " + horaAtual);

        LocalTime horaMeuAniversario = LocalTime.of(15, 25, 15);
        System.out.println("\nHora do meu Aniversário: " + horaMeuAniversario);

        LocalDateTime dataHoraAtual = LocalDateTime.now();
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
        System.out.println("\nData/Hora Formatada: " + dataHoraAtual.format(formatador));
    }
}
