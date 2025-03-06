# API REST - Catálogo Inmobiliaria 🏢🏠

API RESTful para la gestión de un catálogo inmobiliario desarrollada con Spring Boot, que permite administrar propiedades en venta y agentes inmobiliarios.

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0+-brightgreen)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![License](https://img.shields.io/badge/License-MIT-blue)

## 📋 Tabla de Contenidos
- [Descripción](#descripción)
- [Características](#características)
- [Tecnologías](#tecnologías)
- [Requisitos Previos](#requisitos-previos)
- [Instalación y Configuración](#instalación-y-configuración)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Entidades](#entidades)
- [Uso y Endpoints](#uso-y-endpoints)
- [Ejemplos](#ejemplos)
- [Próximas Mejoras](#próximas-mejoras)
- [Licencia](#licencia)
- [Contacto](#contacto)

## 📝 Descripción

API REST desarrollada con Spring Boot que gestiona un catálogo inmobiliario completo. Esta API permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) tanto para propiedades inmobiliarias como para agentes, facilitando la administración de un sistema de listado de propiedades con información detallada.

## ✨ Características

- **Gestión de Propiedades**: 
  - Crear, consultar, actualizar y eliminar propiedades
  - Información detallada de cada propiedad: título, descripción, tipo, ubicación, precio, estado
  - Asignación de propiedades a agentes inmobiliarios

- **Gestión de Agentes**: 
  - Administración de perfiles de agentes inmobiliarios
  - Asignación de propiedades a agentes
  - Información de contacto de agentes (nombre, correo, teléfono)

- **Relaciones**: 
  - Vinculación entre propiedades y agentes (relación Many-to-One)
  - Cada agente puede tener múltiples propiedades asignadas

## 🛠️ Tecnologías

- **Spring Boot**: Framework principal para el desarrollo del backend
- **Spring Data JPA**: Para la persistencia de datos y operaciones de repositorio
- **Spring Web**: Para la creación de endpoints RESTful
- **MySQL**: Base de datos relacional
- **Maven**: Gestión de dependencias
- **Jakarta Persistence API**: Para el mapeo objeto-relacional

## 📋 Requisitos Previos

- Java JDK 17 o superior
- Maven 3.6 o superior
- MySQL 8.0 o superior
- IDE compatible con Spring (IntelliJ IDEA, Eclipse, VS Code)
- Cliente REST (Postman, Insomnia) para pruebas

## 🚀 Instalación y Configuración

1. **Clonar el repositorio**:
   ```bash
   git clone https://github.com/jerorodriguez/springboot-backend-parcial.git
   cd springboot-backend-parcial
   ```

2. **Configurar la base de datos**:

   Crea una base de datos MySQL llamada `db_springboot_backend_parcial`.
   
   El archivo `application.properties` ya está configurado con:
   
   ```properties
   spring.application.name=springboot-backend-parcial
   spring.datasource.url=jdbc:mysql://localhost:3306/db_springboot_backend_parcial
   spring.datasource.username=root
   spring.datasource.password=sasa
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   spring.jpa.show-sql=true
   ```
   
   Asegúrate de modificar las credenciales de acceso según tu entorno.

3. **Compilar el proyecto**:
   ```bash
   mvn clean install
   ```

4. **Ejecutar la aplicación**:
   ```bash
   mvn spring-boot:run
   ```

   La API estará disponible en: `http://localhost:8080`

## 📂 Estructura del Proyecto

```
src/
├── main/
│   ├── java/
│   │   ├── com.jerorodriguez.springboot.inmobiliaria/
│   │   │   ├── config/
│   │   │   ├── controllers/
│   │   │   │   ├── AgenteController.java
│   │   │   │   ├── PropiedadController.java
│   │   │   ├── entities/
│   │   │   │   ├── Agente.java
│   │   │   │   ├── Propiedad.java
│   │   │   ├── repositories/
│   │   │   │   ├── AgenteRepository.java
│   │   │   │   ├── PropiedadRepository.java
│   │   │   ├── services/
│   │   │   │   ├── AgenteService.java
│   │   │   │   ├── AgenteServiceImpl.java
│   │   │   │   ├── PropiedadService.java
│   │   │   │   ├── PropiedadServiceImpl.java
│   │   │   ├── SpringbootBackendParcialApplication.java
│   ├── resources/
│   │   ├── application.properties
├── test/
```

## 🏢 Entidades

### Propiedad

```java
@Entity
@Table(name = "propiedad")
public class Propiedad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;
    private String tipo;
    private String ubicacion;
    private Long price;
    private String estado;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agente_id")
    private Agente agente;
    
    // Getters y setters
}
```

### Agente

```java
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
    
    // Getters y setters
    
    // Helper methods para manejar relaciones
    public void addPropiedad(Propiedad propiedad) {
        propiedades.add(propiedad);
        propiedad.setAgente(this);
    }
    
    public void removePropiedad(Propiedad propiedad) {
        propiedades.remove(propiedad);
        propiedad.setAgente(null);
    }
}
```

## 📡 Uso y Endpoints

### Propiedades

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET    | `/` | Obtener todas las propiedades |
| GET    | `/{id}` | Obtener propiedad por ID |
| POST   | `/` | Crear nueva propiedad |
| PUT    | `/{id}` | Actualizar propiedad existente |
| DELETE | `/{id}` | Eliminar propiedad |
| PUT    | `/{id}/agente/{agenteId}` | Asignar agente a una propiedad |

### Agentes

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET    | `/api/agentes` | Obtener todos los agentes |
| GET    | `/api/agentes/{id}` | Obtener agente por ID |
| POST   | `/api/agentes` | Crear nuevo agente |
| PUT    | `/api/agentes/{id}` | Actualizar agente existente |
| DELETE | `/api/agentes/{id}` | Eliminar agente |

## 📝 Ejemplos

### Crear una propiedad

**Request**:
```http
POST /
Content-Type: application/json

{
  "titulo": "Apartamento moderno en el centro",
  "descripcion": "Hermoso apartamento recién renovado con vistas a la ciudad",
  "tipo": "APARTAMENTO",
  "ubicacion": "Calle Principal 123, Madrid",
  "price": 250000,
  "estado": "DISPONIBLE"
}
```

**Response**:
```json
{
  "id": 1,
  "titulo": "Apartamento moderno en el centro",
  "descripcion": "Hermoso apartamento recién renovado con vistas a la ciudad",
  "tipo": "APARTAMENTO",
  "ubicacion": "Calle Principal 123, Madrid",
  "price": 250000,
  "estado": "DISPONIBLE",
  "agente": null
}
```

### Crear un agente

**Request**:
```http
POST /api/agentes
Content-Type: application/json

{
  "nombre": "María López",
  "correo": "maria.lopez@inmobiliaria.com",
  "telefono": "+34 666 777 888"
}
```

**Response**:
```json
{
  "id": 1,
  "nombre": "María López",
  "correo": "maria.lopez@inmobiliaria.com",
  "telefono": "+34 666 777 888",
  "propiedades": []
}
```

### Asignar un agente a una propiedad

**Request**:
```http
PUT /1/agente/1
```

**Response**:
```json
{
  "id": 1,
  "titulo": "Apartamento moderno en el centro",
  "descripcion": "Hermoso apartamento recién renovado con vistas a la ciudad",
  "tipo": "APARTAMENTO",
  "ubicacion": "Calle Principal 123, Madrid",
  "price": 250000,
  "estado": "DISPONIBLE",
  "agente": {
    "id": 1,
    "nombre": "María López",
    "correo": "maria.lopez@inmobiliaria.com",
    "telefono": "+34 666 777 888"
  }
}
```

## 🔮 Próximas Mejoras

- **Frontend**: Implementación de interfaz de usuario con React/Angular/Vue
- **Documentación API**: Integración de Swagger/OpenAPI para documentar endpoints
- **Búsqueda avanzada**: Implementar filtros para búsqueda de propiedades por tipo, precio, ubicación
- **Autenticación**: Integración de Spring Security con JWT
- **Imágenes**: Soporte para múltiples imágenes por propiedad
- **Validación de datos**: Implementar validaciones más robustas en las entidades
- **Tests unitarios**: Añadir tests para validar la funcionalidad

## 📄 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE.md](LICENSE.md) para más detalles.

## 📞 Contacto

Jeronimo Rodriguez - jeronimoroseag@gmail.com
