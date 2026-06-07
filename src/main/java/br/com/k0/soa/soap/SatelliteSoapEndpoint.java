package br.com.k0.soa.soap;

import br.com.k0.soa.model.Satellite;
import br.com.k0.soa.model.SpaceAlert;
import br.com.k0.soa.service.SatelliteService;
import br.com.k0.soa.service.SpaceAlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import jakarta.xml.bind.annotation.*;

/**
 * Web Service SOAP - K-0 Space Connect
 * Namespace: http://k0.com.br/soa/soap
 */
@Endpoint
@RequiredArgsConstructor
public class SatelliteSoapEndpoint {

    private static final String NAMESPACE = "http://k0.com.br/soa/soap";

    private final SatelliteService satelliteService;
    private final SpaceAlertService alertService;

    /**
     * Operação SOAP 1: Consultar satélite por ID
     */
    @PayloadRoot(namespace = NAMESPACE, localPart = "GetSatelliteRequest")
    @ResponsePayload
    public GetSatelliteResponse getSatellite(@RequestPayload GetSatelliteRequest request) {
        Satellite sat = satelliteService.findById(request.getId());
        GetSatelliteResponse response = new GetSatelliteResponse();
        response.setId(sat.getId());
        response.setName(sat.getName());
        response.setOperatorName(sat.getOperatorName());
        response.setTleLine1(sat.getTleLine1());
        response.setTleLine2(sat.getTleLine2());
        response.setStatus(sat.getStatus().name());
        response.setRegisteredAt(sat.getRegisteredAt().toString());
        return response;
    }

    /**
     * Operação SOAP 2: Registrar alerta via SOAP
     */
    @PayloadRoot(namespace = NAMESPACE, localPart = "RegisterAlertRequest")
    @ResponsePayload
    public RegisterAlertResponse registerAlert(@RequestPayload RegisterAlertRequest request) {
        SpaceAlert alert = alertService.create(
                request.getSatelliteId(),
                request.getAlertType(),
                request.getSeverity(),
                request.getMessage()
        );
        RegisterAlertResponse response = new RegisterAlertResponse();
        response.setAlertId(alert.getId());
        response.setStatus("REGISTERED");
        response.setTimestamp(alert.getCreatedAt().toString());
        return response;
    }

    // ---- Request/Response inner classes ----

    @XmlRootElement(name = "GetSatelliteRequest", namespace = NAMESPACE)
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class GetSatelliteRequest {
        private Long id;
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
    }

    @XmlRootElement(name = "GetSatelliteResponse", namespace = NAMESPACE)
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class GetSatelliteResponse {
        private Long id;
        private String name;
        private String operatorName;
        private String tleLine1;
        private String tleLine2;
        private String status;
        private String registeredAt;
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getOperatorName() { return operatorName; }
        public void setOperatorName(String operatorName) { this.operatorName = operatorName; }
        public String getTleLine1() { return tleLine1; }
        public void setTleLine1(String tleLine1) { this.tleLine1 = tleLine1; }
        public String getTleLine2() { return tleLine2; }
        public void setTleLine2(String tleLine2) { this.tleLine2 = tleLine2; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public String getRegisteredAt() { return registeredAt; }
        public void setRegisteredAt(String registeredAt) { this.registeredAt = registeredAt; }
    }

    @XmlRootElement(name = "RegisterAlertRequest", namespace = NAMESPACE)
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class RegisterAlertRequest {
        private Long satelliteId;
        private String alertType;
        private String severity;
        private String message;
        public Long getSatelliteId() { return satelliteId; }
        public void setSatelliteId(Long satelliteId) { this.satelliteId = satelliteId; }
        public String getAlertType() { return alertType; }
        public void setAlertType(String alertType) { this.alertType = alertType; }
        public String getSeverity() { return severity; }
        public void setSeverity(String severity) { this.severity = severity; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }

    @XmlRootElement(name = "RegisterAlertResponse", namespace = NAMESPACE)
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class RegisterAlertResponse {
        private Long alertId;
        private String status;
        private String timestamp;
        public Long getAlertId() { return alertId; }
        public void setAlertId(Long alertId) { this.alertId = alertId; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public String getTimestamp() { return timestamp; }
        public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
    }
}
