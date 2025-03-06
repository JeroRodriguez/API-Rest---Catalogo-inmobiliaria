package com.jerorodriguez.springboot.inmobiliaria.services;

import com.jerorodriguez.springboot.inmobiliaria.entities.Propiedad;
import com.jerorodriguez.springboot.inmobiliaria.repositories.PropiedadRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PropiedadServiceImpl implements PropiedadService {
    final private PropiedadRepository repository;

    public PropiedadServiceImpl(PropiedadRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Propiedad> findAll() {
        return (List<Propiedad>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Propiedad> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Propiedad save(Propiedad product) {
        return repository.save(product);
    }

    @Override
    @Transactional
    public Optional<Propiedad> deleteById(Long id) {
        Optional<Propiedad> productOptional = repository.findById(id);
        if (productOptional.isPresent()) {
            repository.deleteById(id);
            return productOptional;
        }
        return Optional.empty();
    }
}
