package br.com.fiap.autoatendimento.entrypoint.rest;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/health-check")
public class HealthCheckController {
    
    @GetMapping
    public String healthCheck() {
        return "OK";
    }
}
