package com.jerorodriguez.springboot.inmobiliaria.repositories;

import com.jerorodriguez.springboot.inmobiliaria.entities.Agente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgenteRepository extends JpaRepository<Agente, Long> {
}
