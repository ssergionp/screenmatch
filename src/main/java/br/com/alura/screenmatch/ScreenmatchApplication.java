package br.com.alura.screenmatch;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		ConsumoAPI consumoApi = new ConsumoAPI();
		ConverteDados conversor = new ConverteDados();

		final String API_KEY = "8f317ae9";
		final String BASE_URL = "https://www.omdbapi.com/?t=";
		final String NOME_SERIE = "gilmore+girls";

		var json = consumoApi.obterDados(BASE_URL + NOME_SERIE + "&apikey=" + API_KEY);
		System.out.println("Json: " + json);

		// Busca dados gerais da série
		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
		System.out.println("Dados da Série: " + dados);

		// Busca dados de um episódio específico (Didático)
		json = consumoApi.obterDados(BASE_URL + NOME_SERIE + "&season=1&episode=2&apikey=" + API_KEY);
		DadosEpisodio dadosEpisodio = conversor.obterDados(json, DadosEpisodio.class);
		System.out.println("Dados do Episódio: " + dadosEpisodio);

		// Busca todas as temporadas através de um loop
		List<DadosTemporada> temporadas = new ArrayList<>();
		for (int i = 1; i <= dados.totalTemporadas(); i++) {
			json = consumoApi.obterDados(BASE_URL + NOME_SERIE + "&season=" + i + "&apikey=" + API_KEY);
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
