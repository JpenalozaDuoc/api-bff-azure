package bffapi.bff.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class BffController {

    // Inyecta las URLs de las Azure Functions (puedes cambiarlas si las mueves a application.properties)
    @Value("${azure.function.testOracleConnectionUrl}")
    private String testOracleConnectionUrl;
    
    @Value("${azure.function.getAllRolesUrl}")
    private String getAllRolesUrl;
    
    @Value("${azure.function.getAllUsersUrl}")
    private String getAllUsersUrl;

    private final RestTemplate restTemplate;

    public BffController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Endpoint que llama a la función de conexión a Oracle
    @GetMapping("/testOracleConnection")
    public ResponseEntity<?> testOracleConnection() {
        try {
            return restTemplate.getForEntity(testOracleConnectionUrl, String.class);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al verificar conexión con Oracle.");
        }
    }

    // Endpoint que llama a la función que obtiene todos los roles
    @GetMapping("/getAllRoles")
    public ResponseEntity<?> getAllRoles() {
        try {
            return restTemplate.getForEntity(getAllRolesUrl, String.class);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al obtener roles.");
        }
    }

    // Endpoint que llama a la función que obtiene todos los usuarios
    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers() {
        try {
            return restTemplate.getForEntity(getAllUsersUrl, String.class);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al obtener usuarios.");
        }
    }
}
