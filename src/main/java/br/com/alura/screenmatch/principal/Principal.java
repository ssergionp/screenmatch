package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private final String API_KEY = "&apikey=8f317ae9";
    private final String BASE_URL = "https://www.omdbapi.com/?t=";
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private Scanner leitura = new Scanner(System.in);
    private ConverteDados conversor = new ConverteDados();

    public void exibeMenu() {
        System.out.println("Digite o nome da série: ");
        var nomeSerie = leitura.nextLine();
        var json = consumoApi.obterDados(BASE_URL
                + nomeSerie.replace(" ", "+") + API_KEY);

        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println("Dados da Série: " + dados);

        // Busca todas as temporadas através de um loop
        List<DadosTemporada> temporadas = new ArrayList<>();
        for (int i = 1; i <= dados.totalTemporadas(); i++) {
            json = consumoApi.obterDados(
                    BASE_URL + nomeSerie.replace(" ", "+")
                            + "&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }

        System.out.println("Temporadas da Série:");
        temporadas.forEach(System.out::println);

        // Lista todos os episodios das temporadas
        System.out.println("\nListagem de todos os Episódios:");
        temporadas.forEach(t -> t.episodios().forEach(e ->
                System.out.println("T" + t.numero() + "E" + e.numero() + " - " + e.titulo())
        ));

        temporadas.forEach(System.out::println);

        List<String> nomes = Arrays.asList("Jaqueline", "Iasmin", "Paulo", "João", "Maria");

//        nomes.stream()
//                .sorted()
//                .limit(3)
//                .filter((n -> n.startsWith("J")))
//                .map(n -> n.toUpperCase())
//                .forEach(System.out::println);

        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList()); // Coleção mutável
                // .toList() - Cria coleção imutável

        System.out.println("\nTop 5 Episódios:");
        dadosEpisodios.stream()
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A") )
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                .limit(5)
                .forEach(System.out::println);
    }
}
