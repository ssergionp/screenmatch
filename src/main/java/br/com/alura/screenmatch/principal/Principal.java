package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    }
}
