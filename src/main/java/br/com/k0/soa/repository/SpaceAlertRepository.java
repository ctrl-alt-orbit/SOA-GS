package br.com.k0.soa.repository;

import br.com.k0.soa.model.SpaceAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpaceAlertRepository extends JpaRepository<SpaceAlert, Long> {
    List<SpaceAlert> findBySatelliteId(Long satelliteId);
    List<SpaceAlert> findByAlertType(String alertType);
}
