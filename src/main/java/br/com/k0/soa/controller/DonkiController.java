package br.com.k0.soa.controller;

import br.com.k0.soa.service.DonkiIntegrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Expõe os dados da NASA DONKI via REST.
 * Demonstra integração com serviço externo (requisito SOA).
 */
@RestController
@RequestMapping("/api/space-weather")
@RequiredArgsConstructor
public class DonkiController {

    private final DonkiIntegrationService donkiService;

    /** GET /api/space-weather/cme — Retorna eventos CME da NASA DONKI */
    @GetMapping("/cme")
    public ResponseEntity<List<Map>> getCmeEvents() {
        return ResponseEntity.ok(donkiService.getLatestCmeEvents());
    }
}
