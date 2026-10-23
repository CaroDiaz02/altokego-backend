# AltokeGo Backend

API REST de AltokeGo para gestionar **pasajeros** y **viajes** (un pasajero tiene muchos viajes).
Proyecto de la materia Programación II.

## Integrantes

> Completar con nombre y apellido de **todos** los integrantes del equipo.

- Integrante Carolina Diaz
- Integrante Maria de los Angeles Torres
- Integrante Eliana Carrizo

## Tecnologías

- Java 21
- Spring Boot 3.2.5 (Web, Data JPA, Validation)
- MySQL 8
- Maven (wrapper incluido)

## Configuración de MySQL

1. Tener MySQL en ejecución en `localhost:3306`.
2. Crear la base de datos:

   ```sql
   CREATE DATABASE altokego_db;
   ```

3. Ajustar usuario y contraseña en `src/main/resources/application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/altokego_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
   spring.datasource.username=root
   spring.datasource.password=TU_CONTRASEÑA
   ```

Las tablas `pasajeros` y `viajes` las crea JPA automáticamente (`ddl-auto=update`).

## Cómo ejecutar

```bash
./mvnw spring-boot:run        # Linux / macOS
mvnw.cmd spring-boot:run      # Windows
```

La API queda disponible en `http://localhost:8080`.

## Endpoints

### Pasajeros (`/api/pasajeros`)

| Método | Ruta | Descripción | Respuestas |
|--------|------|-------------|------------|
| POST | `/api/pasajeros` | Crear pasajero | 201, 400, 409 (email repetido) |
| GET | `/api/pasajeros` | Listar pasajeros | 200 |
| GET | `/api/pasajeros/{id}` | Obtener pasajero | 200, 404 |
| PUT | `/api/pasajeros/{id}` | Actualizar pasajero | 200, 400, 404, 409 |
| DELETE | `/api/pasajeros/{id}` | Eliminar pasajero | 204, 404, 409 (tiene viajes) |

Ejemplo de body:

```json
{ "nombre": "Ana Pérez", "email": "ana@mail.com", "telefono": "2644123456" }
```

### Viajes (`/api/viajes`)

| Método | Ruta | Descripción | Respuestas |
|--------|------|-------------|------------|
| POST | `/api/viajes` | Crear viaje | 201, 400, 404 (pasajero inexistente) |
| GET | `/api/viajes` | Listar viajes | 200 |
| GET | `/api/viajes/{id}` | Obtener viaje | 200, 404 |
| GET | `/api/viajes/pasajero/{pasajeroId}` | Viajes de un pasajero | 200, 404 |
| PUT | `/api/viajes/{id}` | Actualizar viaje | 200, 400, 404 |
| DELETE | `/api/viajes/{id}` | Eliminar viaje | 204, 404 |

Ejemplo de body:

```json
{
  "origen": "Caucete",
  "destino": "San Juan",
  "precioArs": 3500.0,
  "metodoPago": "efectivo",
  "pasajeroId": 1
}
```

### Formato de error

```json
{
  "timestamp": "2026-10-21T10:15:30",
  "status": 404,
  "error": "Not Found",
  "mensaje": "Pasajero no encontrado con ID: 99",
  "path": "/api/viajes",
  "detalles": null
}
```

## Pruebas

La colección de Postman está en `postman/AltokeGo.postman_collection.json`
(Postman → Import). Usa la variable `baseUrl` (por defecto `http://localhost:8080`).
Orden sugerido: crear pasajero → crear viaje → listar → obtener por id →
viajes por pasajero → actualizar → eliminar.
