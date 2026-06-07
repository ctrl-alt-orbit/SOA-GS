package br.com.k0.soa.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SatelliteRequest {
    @NotBlank(message = "Nome é obrigatório")
    private String name;

    @NotBlank(message = "Operador é obrigatório")
    private String operatorName;

    private String tleLine1;
    private String tleLine2;
}