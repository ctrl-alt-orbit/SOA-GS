package br.com.k0.soa.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AlertRequest {
    @NotBlank
    private String alertType;

    @NotBlank
    private String severity;

    @NotBlank
    private String message;

    private Long satelliteId;
}