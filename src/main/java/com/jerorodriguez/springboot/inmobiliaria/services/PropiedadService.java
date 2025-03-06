package com.jerorodriguez.springboot.inmobiliaria.services;

import com.jerorodriguez.springboot.inmobiliaria.entities.Propiedad;

import java.util.List;
import java.util.Optional;

public interface PropiedadService {
    List<Propiedad> findAll();
    Optional<Propiedad> findById(Long id);
    Propiedad save(Propiedad product);
    Optional<Propiedad> deleteById(Long id);
}
