# Triana Tourist: points, categories and routes

[English](README.md) · [GitHub](https://github.com/navalcode)

## Resumen

API REST formativa con **14 pruebas locales**: el contexto original y 13 recorridos HTTP/JPA. La revisión conserva los nombres de propiedades de DTO/errores y permite reproducir los casos de fallo.

Editar un punto usa el `cat_id` solicitado, en lugar de buscar una categoría con el ID del punto. Los errores al editar/eliminar una ruta ausente identifican Route. Coordenadas inválidas, IDs/nombres nulos, pasos repetidos y JSON mal formado devuelven 400. Un método no admitido conserva 405 y la cabecera Allow. Añadir a una ruta un punto ya incluido devuelve 400 sin cambiar los pasos.

Rutas y categorías pueden mantener su nombre al editarse; otro registro no puede apropiarse del nombre existente. La unicidad se comprueba en los servicios de guardado/edición; el validador de pasos compara IDs sin consultas ni comparaciones entre IDs y objetos Poi. Son comprobaciones de peticiones secuenciales de demo, sin garantía de base de datos frente a duplicados concurrentes.

| Petición | Comportamiento esperado |
|---|---|
| POST `/category/` con un nombre | 201 e ID generado |
| POST `/poi/` con `name`, `location`, `cat_id`, `coverPhoto` | 201; coordenadas `latitud,longitud` |
| POST `/route/` con `name` e `ids` | 201, IDs ordenados y sin repetir |
| PUT `/route/{id}` manteniendo su nombre | 200 con datos válidos |
| DELETE `/poi/{id}` | 204; las rutas conservan los otros puntos |

Empieza por [los recorridos HTTP](TrianaTourist/src/test/java/com/salesianos/triana/dam/TrianaTourist/TouristHttpJourneyTest.java), después RouteService/PoiService y el tratamiento de errores. Los datos son ficticios; las imágenes example.invalid no se descargan. No representa una interfaz de mapas ni integración externa. Mantiene las herramientas históricas; no se acredita CI alojada, autenticación, despliegue persistente ni concurrencia.

## Ejecutar

Un arranque independiente del JAR en loopback supera 9 peticiones HTTP reales con su configuración en memoria por defecto y se cierra correctamente.

Requiere JDK 17. Desde la raíz del repositorio:

```sh
cd TrianaTourist
sh ./mvnw verify
java -jar target/TrianaTourist-0.0.1-SNAPSHOT.jar --server.port=0
```

Los valores por defecto usan H2 en memoria, consola H2 desactivada y dirección loopback. La base desaparece al terminar; no se abre el fichero DB original. Tomcat imprime el puerto asignado por el sistema.

Resultados locales con JDK 17/macOS del 29 de octubre de 2026 en [verification.json](docs/verification.json). No equivalen a un servicio de producción. [Decisiones de diseño y contrato](DESIGN.md) · [Componentes y atribución](THIRD_PARTY.md).
