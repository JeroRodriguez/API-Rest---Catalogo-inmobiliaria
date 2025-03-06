package com.jerorodriguez.springboot.inmobiliaria.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "agente")
public class Agente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String correo;
    private String telefono;

    @OneToMany(mappedBy = "agente", cascade = CascadeType.ALL)
    private List<Propiedad> propiedades = new ArrayList<>();

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Propiedad> getPropiedades() {
        return propiedades;
    }

    public void setPropiedades(List<Propiedad> propiedades) {
        this.propiedades = propiedades;
    }

    // Helper method para añadir una propiedad a este agente
    public void addPropiedad(Propiedad propiedad) {
        propiedades.add(propiedad);
        propiedad.setAgente(this);
    }

    // Helper method para eliminar una propiedad de este agente
    public void removePropiedad(Propiedad propiedad) {
        propiedades.remove(propiedad);
        propiedad.setAgente(null);
    }
}
