package br.com.k0.soa.service;

import br.com.k0.soa.exception.ResourceNotFoundException;
import br.com.k0.soa.model.AlertSeverity;
import br.com.k0.soa.model.SpaceAlert;
import br.com.k0.soa.model.Satellite;
import br.com.k0.soa.repository.SpaceAlertRepository;
import br.com.k0.soa.repository.SatelliteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpaceAlertService {

    private final SpaceAlertRepository alertRepository;
    private final SatelliteRepository satelliteRepository;

    public List<SpaceAlert> findAll() {
        return alertRepository.findAll();
    }

    public SpaceAlert findById(Long id) {
        return alertRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alerta não encontrado: " + id));
    }

    public SpaceAlert create(Long satelliteId, String alertType, String severity, String message) {
        Satellite satellite = satelliteRepository.findById(satelliteId)
                .orElseThrow(() -> new ResourceNotFoundException("Satélite não encontrado: " + satelliteId));

        SpaceAlert alert = new SpaceAlert();
        alert.setSatellite(satellite);
        alert.setAlertType(alertType);
        alert.setSeverity(AlertSeverity.valueOf(severity.toUpperCase()));
        alert.setMessage(message);
        return alertRepository.save(alert);
    }

    public List<SpaceAlert> findBySatellite(Long satelliteId) {
        return alertRepository.findBySatelliteId(satelliteId);
    }

    public void delete(Long id) {
        SpaceAlert alert = findById(id);
        alertRepository.delete(alert);
    }
}
