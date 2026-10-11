package pe.edu.cibertec.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConfigServerApplicationTests {

    @Test
    void clasePrincipalExiste() {
        // El servicio depende de Eureka/Config en ejecucion; aqui solo se valida la clase principal.
        assertNotNull(new ConfigServerApplication());
    }
}
