package com.equityrisk.platform;
import com.equityrisk.platform.service.HealthService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HealthServiceTest {

    HealthService healthService = new HealthService();

    @Test
    @DisplayName("Should return UP when checking health")
    void shouldReturnUpWhenCheckingHealth(){
        assertEquals("UP", healthService.getStatus());
    }
}
