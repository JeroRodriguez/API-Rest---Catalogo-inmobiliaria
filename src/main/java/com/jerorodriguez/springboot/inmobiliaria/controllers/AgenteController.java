package com.jerorodriguez.springboot.inmobiliaria.controllers;

import com.jerorodriguez.springboot.inmobiliaria.entities.Agente;
import com.jerorodriguez.springboot.inmobiliaria.services.AgenteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agentes")
public class AgenteController {

    private final AgenteService agenteService;

    public AgenteController(AgenteService agenteService) {
        this.agenteService = agenteService;
    }

    @GetMapping
    public List<Agente> listar() {
        return agenteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        return agenteService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Agente agente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(agenteService.save(agente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Agente agente, @PathVariable Long id) {
        return agenteService.findById(id)
                .map(a -> {
                    a.setNombre(agente.getNombre());
                    a.setCorreo(agente.getCorreo());
                    a.setTelefono(agente.getTelefono());
                    return ResponseEntity.ok(agenteService.save(a));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        return agenteService.deleteById(id)
                .map(a -> ResponseEntity.ok().build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
