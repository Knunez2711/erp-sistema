# ERP Sistema — Backend Java + Frontend Bootstrap

> Sistema de gestión empresarial (ERP) construido con **Java 17**, **Spring Boot 3** y **Clean Architecture**. Módulo de autenticación con JWT, dashboard interactivo y base sólida para escalar hacia un ERP completo.

---

## Pantallas

| Login | Dashboard |
|-------|-----------|
| Autenticación segura con JWT | Panel principal con KPIs, pedidos y alertas |

---

## Tecnologías

### Backend
| Tecnología | Versión | Uso |
|---|---|---|
| Java | 17 | Lenguaje principal |
| Spring Boot | 3.2.4 | Framework principal |
| Spring Security | 6.x | Autenticación y autorización |
| Spring Data JPA | 3.x | Persistencia ORM |
| JWT (jjwt) | 0.11.5 | Tokens de autenticación stateless |
| H2 Database | — | Base de datos en memoria (desarrollo) |
| Maven | 3.x | Gestión de dependencias y build |

### Frontend
| Tecnología | Uso |
|---|---|
| Bootstrap 5.3 | UI components y layout responsivo |
| Bootstrap Icons | Iconografía |
| HTML5 + JavaScript vanilla | Páginas y consumo de la API REST |

---

## Arquitectura

El proyecto sigue **Clean Architecture** (Arquitectura Hexagonal), separando las responsabilidades en 4 capas independientes:

```
src/main/java/com/erp/
│
├── domain/                     # Núcleo del negocio (sin dependencias externas)
│   ├── model/User.java         # Entidad de dominio pura
│   └── exception/              # Excepciones de negocio
│
├── application/                # Casos de uso
│   └── usecase/AuthService.java
│
├── infrastructure/             # Adaptadores externos
│   ├── persistence/UserEntity.java
│   ├── repository/UserRepository.java
│   └── security/               # JWT + Spring Security
│
└── interfaces/                 # Puntos de entrada
    └── rest/                   # Controladores REST + DTOs
```

**¿Por qué Clean Architecture?**
- El dominio no depende de Spring, JPA ni ningún framework
- Se puede cambiar la base de datos sin tocar la lógica de negocio
- Cada capa tiene una responsabilidad única y clara
- Facilita el testing y la escalabilidad

---

## Endpoints API

### Autenticación

| Método | Endpoint | Descripción | Auth |
|--------|----------|-------------|------|
| `POST` | `/auth/register` | Registrar nuevo usuario | No |
| `POST` | `/auth/login` | Iniciar sesión | No |

#### Ejemplo: Registro
```bash
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{"email": "admin@erp.com", "password": "12345678"}'
```

#### Respuesta exitosa
```
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBlcnAuY29tIi...
```

#### Errores controlados
| HTTP | Situación |
|------|-----------|
| `400` | Datos inválidos (email mal formado, contraseña corta) |
| `401` | Credenciales incorrectas |
| `404` | Usuario no encontrado |
| `409` | Email ya registrado |

---

## Seguridad

- Contraseñas hasheadas con **BCrypt** — nunca se guarda texto plano
- Autenticación **stateless** con JWT (sin sesiones en servidor)
- Tokens firmados con **HS256**, expiración configurable (24h por defecto)
- Secret JWT configurable via variable de entorno `JWT_SECRET`

---

## Instalación y ejecución

### Requisitos
- Java 17+
- Maven 3.8+

### Clonar y ejecutar

```bash
git clone https://github.com/tu-usuario/erp-sistema.git
cd erp-sistema
mvn spring-boot:run
```

El servidor arranca en `http://localhost:8080`

### Variables de entorno (producción)

```bash
export JWT_SECRET=tu-clave-secreta-en-base64-de-32-bytes
```

En desarrollo se usa un valor por defecto definido en `application.properties`.

---

## Frontend

| Página | URL |
|--------|-----|
| Login | `http://localhost:8080/login.html` |
| Registro | `http://localhost:8080/register.html` |
| Dashboard | `http://localhost:8080/dashboard.html` |
| H2 Console (dev) | `http://localhost:8080/h2-console` |

---

## Módulos del ERP (Roadmap)

- [x] Autenticación con JWT
- [x] Dashboard principal con KPIs
- [ ] Módulo de Ventas
- [ ] Módulo de Inventario
- [ ] Módulo de Clientes (CRM)
- [ ] Módulo de Compras y Proveedores
- [ ] Módulo de Facturación
- [ ] Reportes y Analytics
- [ ] Gestión de Roles y Permisos
- [ ] Migración a PostgreSQL
- [ ] Dockerización

---

## Estructura del proyecto

```
ERP/
├── pom.xml
├── README.md
└── src/
    └── main/
        ├── java/com/erp/
        │   ├── ErpApplication.java
        │   ├── domain/
        │   ├── application/
        │   ├── infrastructure/
        │   └── interfaces/
        └── resources/
            ├── application.properties
            └── static/
                ├── index.html
                ├── login.html
                ├── register.html
                └── dashboard.html
