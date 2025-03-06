package com.jerorodriguez.springboot.inmobiliaria.repositories;

import com.jerorodriguez.springboot.inmobiliaria.entities.Propiedad;
import org.springframework.data.repository.CrudRepository;

public interface PropiedadRepository extends CrudRepository<Propiedad, Long> {
}
