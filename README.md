# API de Tienda

Esta API de Tienda está desarrollada utilizando Java 11, Spring Boot, y Gradle. Se conecta a una base de datos PostgreSQL y sigue la arquitectura Domain Driven Development (DDD). La API permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) de productos, así como consultas específicas como obtener productos por categoría.

## Tecnologías Utilizadas

- Java 11
- Spring Boot
- Gradle
- PostgreSQL
- IntelliJ IDEA
- Swagger

## Arquitectura: Domain Driven Development (DDD)

DDD es una metodología de diseño de software que estructura el código alrededor del dominio del problema. Algunas ventajas clave de esta arquitectura son:

- **Claridad en el Dominio**: El código refleja directamente el modelo del negocio, facilitando la comprensión y mantenimiento del sistema.
- **Separación de Responsabilidades**: División clara entre las capas de dominio, aplicación e infraestructura, lo que facilita las pruebas y la evolución del software.
- **Flexibilidad y Escalabilidad**: Permite escalar y adaptar el sistema a medida que los requisitos del negocio cambian.

## Documentación de la API

La API está documentada utilizando Swagger, lo que facilita la comprensión de los endpoints disponibles y cómo interactuar con ellos. Puedes acceder a la documentación en [Swagger UI](http://localhost:8090/tienda/api/swagger-ui/index.html).

## Instalación y Uso

Para utilizar esta API en tu entorno local, sigue estos pasos:

1. Clona este repositorio.
2. Configura tu base de datos PostgreSQL y actualiza las credenciales en `application.properties`.
3. Ejecuta la aplicación utilizando Gradle: `./gradlew bootRun`.
4. Accede a la documentación de la API en [Swagger UI](http://localhost:8090/tienda/api/swagger-ui/index.html).

## Ejemplos de Uso

```http
POST [http://localhost:8090/tienda/api/productos/guardar]
Content-Type: application/json

{
    "renombre": "Lechuga Bataviaa",
    "categoriaId": 1,
    "precio": 300.0,
    "inventario": 500,
    "activo": true
}
```
