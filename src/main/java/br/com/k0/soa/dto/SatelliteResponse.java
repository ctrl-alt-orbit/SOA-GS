package br.com.k0.soa.dto;

import br.com.k0.soa.model.SatelliteStatus;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SatelliteResponse {
    private Long id;
    private String name;
    private String operatorName;
    private String tleLine1;
    private String tleLine2;
    private SatelliteStatus status;
    private LocalDateTime registeredAt;
    private String assetType;
    private String summary;
}