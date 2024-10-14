package br.com.fiap.autoatendimento.entrypoint.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/health-check")
public class HealthCheckController {

    @Value("${spring.application.name}")
    private String APP_NAME;

    @GetMapping
    public String healthCheck() {
        return ("Application: " + APP_NAME + " is running");
    }
}
