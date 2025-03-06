package com.jerorodriguez.springboot.inmobiliaria.services;

import com.jerorodriguez.springboot.inmobiliaria.entities.Agente;
import java.util.List;
import java.util.Optional;

public interface AgenteService {
    List<Agente> findAll();
    Optional<Agente> findById(Long id);
    Agente save(Agente agente);
    Optional<Agente> deleteById(Long id);
}
