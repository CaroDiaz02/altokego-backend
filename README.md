# AltokeGo - Backend API

API REST desarrollada para el sistema de gestion de transporte AltokeGo, construida con Java y Spring Boot. Este proyecto forma parte del desarrollo backend enfocado en seguridad, control de acceso por roles, persistencia con base de datos relacional y manejo de codigos de estado HTTP.

---

## Tecnologias Utilizadas

* Java 21
* Spring Boot 3.2.5
* Spring Security (Autenticacion Basic Auth y gestion de roles)
* Spring Data JPA / Hibernate
* Maven (Gestor de dependencias)
* MySQL / XAMPP (Base de datos relacional)

---

## Seguridad y Arquitectura

El sistema implementa una arquitectura de seguridad stateless utilizando Spring Security:
* Endpoints Publicos: Registro de usuarios (`/api/auth/register`) y manejo de errores (`/error`).
* Endpoints Protegidos: Las rutas de gestion (ej. eliminacion de viajes en `/api/viajes/**`) requieren autenticacion mediante Basic Auth y validacion estricta de roles (`ROLE_ADMIN`).
* Cifrado: Las contraseñas de los usuarios se almacenan de forma segura utilizando BCryptPasswordEncoder.

---

## Configuracion y Ejecucion Local

### Prerrequisitos
1. Tener instalado Java 21 y Maven.
2. Tener en ejecucion un servidor MySQL (por ejemplo, mediante XAMPP / phpMyAdmin).

### 1. Clonar el repositorio
```bash
git clone [https://github.com/tu-usuario/altokego-backend.git](https://github.com/tu-usuario/altokego-backend.git)
cd altokego-backend


## Integrantes del Grupo
* Torres Maria
* Carrizo Eliana
* Diaz Carolina