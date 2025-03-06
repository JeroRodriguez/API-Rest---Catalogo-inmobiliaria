package com.jerorodriguez.springboot.inmobiliaria.services;

import com.jerorodriguez.springboot.inmobiliaria.entities.Agente;
import com.jerorodriguez.springboot.inmobiliaria.repositories.AgenteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AgenteServiceImpl implements AgenteService {
    final private AgenteRepository repository;

    public AgenteServiceImpl(AgenteRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Agente> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Agente> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Agente save(Agente agente) {
        return repository.save(agente);
    }

    @Override
    @Transactional
    public Optional<Agente> deleteById(Long id) {
        Optional<Agente> agenteOptional = repository.findById(id);
        if (agenteOptional.isPresent()) {
            repository.deleteById(id);
            return agenteOptional;
        }
        return Optional.empty();
    }
}
