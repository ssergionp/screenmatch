package br.com.alura.screenmatch.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {

    /**
     * Método responsável por realizar uma requisição GET para um endereço (URL)
     * e retornar o conteúdo da resposta como uma String (geralmente um JSON).
     */
    public String obterDados(String endereco) {
        // Cria um cliente HTTP padrão para enviar a requisição
        HttpClient client = HttpClient.newHttpClient();

        // Constrói a requisição HTTP configurando a URI de destino
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();

        HttpResponse<String> response = null;
        try {
            // Tenta enviar a requisição de forma síncrona
            // O BodyHandlers.ofString() indica que queremos o corpo da resposta como Texto
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            // Erros de conexão ou rede
            throw new RuntimeException("Erro de rede ao tentar acessar a API: " + e.getMessage());
        } catch (InterruptedException e) {
            // Erro caso a thread atual seja interrompida durante a requisição
            throw new RuntimeException("A requisição foi interrompida: " + e.getMessage());
        }

        // Extrai o corpo (JSON) da resposta recebida
        String json = response.body();
        return json;
    }
}
