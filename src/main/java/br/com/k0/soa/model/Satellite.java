package br.com.k0.soa.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "satellites")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Satellite extends SpaceAsset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Operador é obrigatório")
    @Column(name = "operator_name", nullable = false)
    private String operatorName;

    @Column(name = "tle_line1")
    private String tleLine1;

    @Column(name = "tle_line2")
    private String tleLine2;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SatelliteStatus status = SatelliteStatus.NOMINAL;

    @Column(name = "registered_at", nullable = false)
    private LocalDateTime registeredAt = LocalDateTime.now();

    // Polimorfismo: sobrescreve o método abstrato de SpaceAsset
    @Override
    public String getAssetType() {
        return "SATELLITE";
    }

    @Override
    public String getSummary() {
        return String.format("Satélite %s [%s] - Status: %s", name, operatorName, status);
    }
}
