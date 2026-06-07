package br.com.k0.soa.repository;

import br.com.k0.soa.model.Satellite;
import br.com.k0.soa.model.SatelliteStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SatelliteRepository extends JpaRepository<Satellite, Long> {
    List<Satellite> findByStatus(SatelliteStatus status);
    List<Satellite> findByOperatorNameIgnoreCase(String operatorName);
    boolean existsByNameAndOperatorName(String name, String operatorName);
}
