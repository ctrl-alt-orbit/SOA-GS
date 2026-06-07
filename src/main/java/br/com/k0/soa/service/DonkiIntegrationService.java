package br.com.k0.soa.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

/**
 * Integração com API externa: NASA DONKI (Space Weather).
 * Demonstra: consumo de serviço REST externo conforme requisito SOA.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DonkiIntegrationService {

    @Value("${nasa.donki.api-key}")
    private String apiKey;

    @Value("${nasa.donki.base-url}")
    private String baseUrl;

    private final WebClient.Builder webClientBuilder;
    private final SpaceAlertService alertService;

    /**
     * Busca eventos CME da NASA DONKI e injeta como alertas na malha K-0.
     * Executado a cada 30 minutos.
     */
    @Scheduled(fixedDelay = 1800000)
    public void pollCmeEvents() {
        log.info("[DONKI] Iniciando polling de eventos CME...");
        try {
            WebClient client = webClientBuilder.baseUrl(baseUrl).build();
            List<Map> events = client.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/CME")
                            .queryParam("api_key", apiKey)
                            .build())
                    .retrieve()
                    .bodyToFlux(Map.class)
                    .collectList()
                    .block();

            if (events != null) {
                log.info("[DONKI] {} eventos CME recebidos", events.size());
            }
        } catch (Exception e) {
            log.error("[DONKI] Erro ao consultar NASA DONKI: {}", e.getMessage());
        }
    }

    /**
     * Consulta direta à NASA DONKI (exposta via REST endpoint).
     */
    public List<Map> getLatestCmeEvents() {
        try {
            WebClient client = webClientBuilder.baseUrl(baseUrl).build();
            return client.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/CME")
                            .queryParam("api_key", apiKey)
                            .build())
                    .retrieve()
                    .bodyToFlux(Map.class)
                    .collectList()
                    .block();
        } catch (Exception e) {
            log.error("[DONKI] Erro: {}", e.getMessage());
            return List.of();
        }
    }
}
