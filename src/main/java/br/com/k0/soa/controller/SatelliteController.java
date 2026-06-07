package br.com.k0.soa.controller;

import br.com.k0.soa.dto.SatelliteRequest;
import br.com.k0.soa.model.Satellite;
import br.com.k0.soa.model.SatelliteStatus;
import br.com.k0.soa.service.SatelliteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * API REST - Entidade principal: Satellite
 * CRUD completo conforme requisito SOA.
 */
@RestController
@RequestMapping("/api/satellites")
@RequiredArgsConstructor
public class SatelliteController {

    private final SatelliteService service;

    /** GET /api/satellites — Lista todos os satélites */
    @GetMapping
    public ResponseEntity<List<Satellite>> getAll(
            @RequestParam(required = false) String status) {
        if (status != null) {
            return ResponseEntity.ok(service.findByStatus(SatelliteStatus.valueOf(status.toUpperCase())));
        }
        return ResponseEntity.ok(service.findAll());
    }

    /** GET /api/satellites/{id} — Busca satélite por ID */
    @GetMapping("/{id}")
    public ResponseEntity<Satellite> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    /** POST /api/satellites — Cria novo satélite */
    @PostMapping
    public ResponseEntity<Satellite> create(@Valid @RequestBody SatelliteRequest request) {
        Satellite created = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /** PUT /api/satellites/{id} — Atualiza satélite */
    @PutMapping("/{id}")
    public ResponseEntity<Satellite> update(
            @PathVariable Long id,
            @Valid @RequestBody SatelliteRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    /** DELETE /api/satellites/{id} — Remove satélite */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    /** PATCH /api/satellites/{id}/status — Atualiza status */
    @PatchMapping("/{id}/status")
    public ResponseEntity<Satellite> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return ResponseEntity.ok(service.updateStatus(id, SatelliteStatus.valueOf(status.toUpperCase())));
    }
}
