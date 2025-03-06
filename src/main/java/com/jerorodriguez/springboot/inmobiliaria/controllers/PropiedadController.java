package com.jerorodriguez.springboot.inmobiliaria.controllers;

import com.jerorodriguez.springboot.inmobiliaria.entities.Propiedad;
import com.jerorodriguez.springboot.inmobiliaria.services.AgenteService;
import com.jerorodriguez.springboot.inmobiliaria.services.PropiedadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PropiedadController {
    final private PropiedadService service;

    private final AgenteService agenteService;

    public PropiedadController(PropiedadService service, AgenteService agenteService) {
        this.service = service;
        this.agenteService = agenteService;
    }

    @GetMapping
    public ResponseEntity<List<Propiedad>> list() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Propiedad> details(@PathVariable Long id) {
        Optional<Propiedad> optionalPropiedad = service.findById(id);
        if (optionalPropiedad.isPresent()) {
            return ResponseEntity.ok(optionalPropiedad.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Propiedad> create(@RequestBody Propiedad propiedad) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(propiedad));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Propiedad> update(@PathVariable Long id, @RequestBody Propiedad propiedad) {
        Optional<Propiedad> optionalPropiedad = service.findById(id);
        if (optionalPropiedad.isPresent()) {
            Propiedad propiedadDb = optionalPropiedad.orElseThrow();
            propiedadDb.setTitulo(propiedad.getTitulo());
            propiedadDb.setDescripcion(propiedad.getDescripcion());
            propiedadDb.setTipo(propiedad.getTipo());
            propiedadDb.setUbicacion(propiedad.getUbicacion());
            propiedadDb.setPrice(propiedad.getPrice());
            propiedadDb.setEstado(propiedad.getEstado());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(propiedadDb));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Propiedad> delete(@PathVariable Long id) {
        Optional<Propiedad> optionalPropiedad = service.deleteById(id);
        if (optionalPropiedad.isPresent()) {
            Propiedad propiedadDeleted = optionalPropiedad.orElseThrow();
            return ResponseEntity.status(HttpStatus.OK).body(propiedadDeleted);
        }
        return ResponseEntity.notFound().build();
    }

    // En tu PropiedadController, añade este método:

    @PutMapping("/{id}/agente/{agenteId}")
    public ResponseEntity<?> asignarAgente(@PathVariable Long id, @PathVariable Long agenteId) {
        return service.findById(id)
                .map(propiedad -> {
                    return agenteService.findById(agenteId)
                            .map(agente -> {
                                propiedad.setAgente(agente);
                                return ResponseEntity.ok(service.save(propiedad));
                            })
                            .orElseGet(() -> ResponseEntity.notFound().build());
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
