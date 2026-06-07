package br.com.k0.soa.controller;

import br.com.k0.soa.model.SpaceAlert;
import br.com.k0.soa.service.SpaceAlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/alerts")
@RequiredArgsConstructor
public class SpaceAlertController {

    private final SpaceAlertService service;

    @GetMapping
    public ResponseEntity<List<SpaceAlert>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpaceAlert> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/satellite/{satelliteId}")
    public ResponseEntity<List<SpaceAlert>> getBySatellite(@PathVariable Long satelliteId) {
        return ResponseEntity.ok(service.findBySatellite(satelliteId));
    }

    @PostMapping
    public ResponseEntity<SpaceAlert> create(@RequestBody Map<String, String> body) {
        SpaceAlert alert = service.create(
                Long.parseLong(body.get("satelliteId")),
                body.get("alertType"),
                body.get("severity"),
                body.get("message")
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(alert);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
