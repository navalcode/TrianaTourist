# Triana Tourist: points, categories and routes

[Español](README.es.md) · [GitHub](https://github.com/navalcode)

## Overview

A coursework REST API with **14 local tests**: the original context and 13 HTTP/JPA journeys. The review preserves DTO/error envelope property names and adds reproducible failure behavior.

Editing a point uses the requested `cat_id`, rather than treating the point ID as a category ID. Missing route edit/delete errors identify Route. Invalid coordinates, null IDs/names, duplicate steps and malformed JSON return 400. Unsupported methods keep 405 and the Allow header. Appending an existing point to a route returns 400 without changing its steps.

Route/category names may remain unchanged on update, while a different record cannot take an existing name. Uniqueness is checked inside save/update services; the duplicate-step validator compares IDs without database lookups or comparing IDs with Poi objects. These checks are for sequential demo requests, not a database-backed guarantee against concurrent duplicates.

| Request | Expected behavior |
|---|---|
| POST `/category/` with a name | 201, generated category ID |
| POST `/poi/` with `name`, `location`, `cat_id`, `coverPhoto` | 201; coordinates use `latitude,longitude` |
| POST `/route/` with `name` and `ids` | 201, ordered point IDs, no repeated ID |
| PUT `/route/{id}` with its existing name | 200 when input is valid |
| DELETE `/poi/{id}` | 204; routes retain their other points |

Read [the HTTP journeys](TrianaTourist/src/test/java/com/salesianos/triana/dam/TrianaTourist/TouristHttpJourneyTest.java), then RouteService/PoiService and error advice. Fixtures are fictional and example.invalid images are never fetched. No map UI or external provider is implied. The historical toolchain remains; there is no hosted CI, authentication, persistent deployment or concurrency validation in this review.

## Run

A separate loopback run of the packaged JAR passed 9 actual HTTP requests with its default memory configuration, then shut down cleanly.

Requires JDK 17. From the repository root:

```sh
cd TrianaTourist
sh ./mvnw verify
java -jar target/TrianaTourist-0.0.1-SNAPSHOT.jar --server.port=0
```

Defaults use memory-only H2, a disabled H2 console and loopback binding. Data disappears on shutdown; the original file DB is not opened. Tomcat prints the OS-assigned port.

Local JDK 17/macOS results from 29 October 2026 are recorded in [verification.json](docs/verification.json). They do not establish production readiness. [Design and contract decisions](DESIGN.md) · [Components and attribution](THIRD_PARTY.md).

## Resumen

[La versión española completa](README.es.md) cubre los mismos contratos, comandos y límites.

## Original notes

The historical notes below are retained verbatim; the reviewed commands and current behavior above take precedence.

<details>
<summary>Original documentation and credits · Documentación y créditos originales</summary>

# TrianaTourist
</details>
