package br.com.k0.soa.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AlertResponse {
    private Long id;
    private Long satelliteId;
    private String satelliteName;
    private String alertType;
    private String severity;
    private String message;
    private LocalDateTime createdAt;
}