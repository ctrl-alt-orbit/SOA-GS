package br.com.k0.soa.service;

import br.com.k0.soa.dto.SatelliteRequest;
import br.com.k0.soa.exception.ResourceNotFoundException;
import br.com.k0.soa.model.Satellite;
import br.com.k0.soa.model.SatelliteStatus;
import br.com.k0.soa.repository.SatelliteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SatelliteService {

    private final SatelliteRepository repository;

    public List<Satellite> findAll() {
        return repository.findAll();
    }

    public Satellite findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Satélite não encontrado: " + id));
    }

    public Satellite create(SatelliteRequest req) {
        Satellite satellite = new Satellite();
        satellite.setName(req.getName());
        satellite.setOperatorName(req.getOperatorName());
        satellite.setTleLine1(req.getTleLine1());
        satellite.setTleLine2(req.getTleLine2());
        satellite.setStatus(SatelliteStatus.NOMINAL);
        return repository.save(satellite);
    }

    public Satellite update(Long id, SatelliteRequest req) {
        Satellite satellite = findById(id);
        satellite.setName(req.getName());
        satellite.setOperatorName(req.getOperatorName());
        satellite.setTleLine1(req.getTleLine1());
        satellite.setTleLine2(req.getTleLine2());
        return repository.save(satellite);
    }

    public void delete(Long id) {
        Satellite satellite = findById(id);
        repository.delete(satellite);
    }

    public Satellite updateStatus(Long id, SatelliteStatus status) {
        Satellite satellite = findById(id);
        satellite.setStatus(status);
        return repository.save(satellite);
    }

    public List<Satellite> findByStatus(SatelliteStatus status) {
        return repository.findByStatus(status);
    }
}
