package com.orbitech.npvet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/npvet-api/health")
public class HealthController {

    @GetMapping
    public ResponseEntity<String> healthCheck() {
        try {
            log.info("Health check iniciado");
            log.debug("Verificando status da aplicação");
            
            String message = "Aplicação está funcionando normalmente";
            log.info("Health check finalizado com sucesso");
            
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            log.error("Erro crítico durante health check: {}", e.getMessage(), e);
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao verificar status da aplicação");
        }
    }
}
