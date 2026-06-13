# Blog Platform — Full-Stack CRUD App with Spring Boot, JPA & Thymeleaf

A full-stack MVC blogging application built using **Java 21**, **Spring Boot 4.x**, and **PostgreSQL**.

This platform provides a complete content management lifecycle featuring:

- Layered architecture (Controller → Service → Repository)
- Data validation
- Automated relational schema management via Hibernate
- Server-side rendering with Thymeleaf

---

# Tech Stack

| Layer | Technology |
|---|---|
| Back-End | Spring Boot 4.0.6 & Java 21 (JDK 21) |
| Data Access | Spring Data JPA & Hibernate ORM 7.2 |
| Database | PostgreSQL 18.3 with HikariCP |
| Front-End | Thymeleaf + Bootstrap 5.3 CDN |
| Tooling | IntelliJ IDEA & Postman |

---

# Core Features

## 1. Layered Routing

Clean separation between:

- REST APIs using `@RestController`
- Server-rendered view pipelines using `@Controller`

---

## 2. Relational Mapping

- One `User` maps to many `Post` records
- Cascade lifecycle operations enabled using `CascadeType.ALL`
- Serialization recursion prevention handled via `@JsonIgnore`

---

## 3. Transaction Management

Database operations are wrapped inside transactional service layers to ensure:

- Safe rollback on failure
- Atomic database consistency
- Isolation between concurrent operations

---

## 4. Full CRUD Functionality

Supports live browser-driven functionality including:

- Registering authors
- Publishing blog posts
- Editing and updating records
- Cascade deleting linked posts

---

# Local Setup Guide

## 1. Clone the Repository

```bash
git clone https://github.com/abhinavdevaradesi/blog-platform.git
cd blog-platform
```

---

## 2. Configure PostgreSQL Database

Open PostgreSQL or pgAdmin and create the target database:

```sql
CREATE DATABASE blogdb;
```

---

## 3. Configure `application.properties`

Navigate to:

```text
src/main/resources/application.properties
```

Paste the following configuration:

```properties
# Server Port
server.port=8081

# PostgreSQL Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/blogdb?createDatabaseIfNotExist=true
spring.datasource.username=postgres
spring.datasource.password=YOUR_POSTGRES_PASSWORD_HERE
spring.datasource.driver-class-name=org.postgresql.Driver

# Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.open-in-view=false
```

---

## 4. Build & Run the Application

```bash
mvn clean install
mvn spring-boot:run
```

### IntelliJ IDEA Alternative

Run the application directly by launching `BlogPlatformApplication.java` using the green Run button.

---

## 5. Access the Platform

Once Tomcat starts successfully, open:

```text
http://localhost:8081/
```

---

# API Endpoints

All endpoints can be tested using Postman.

---

## Register New User

**Endpoint:** `POST /api/users`

**URL:** `http://localhost:8081/api/users`

**Sample Request:**

```json
{
  "fullName": "John Doe",
  "username": "johndoe",
  "email": "john@example.com"
}
```

**Sample Response:**

```json
{
  "id": 1,
  "fullName": "John Doe",
  "username": "johndoe",
  "email": "john@example.com"
}
```

---

## Fetch All Users

**Endpoint:** `GET /api/users`

**URL:** `http://localhost:8081/api/users`

**Sample Response:**

```json
[
  {
    "id": 1,
    "fullName": "John Doe",
    "username": "johndoe",
    "email": "john@example.com"
  }
]
```

---

## Create Post for User

**Endpoint:** `POST /api/posts/user/{id}`

**Example:** `http://localhost:8081/api/posts/user/1`

**Sample Request:**

```json
{
  "title": "My First Blog",
  "content": "Spring Boot relational systems are powerful."
}
```

**Sample Response:**

```json
{
  "id": 1,
  "title": "My First Blog",
  "content": "Spring Boot relational systems are powerful."
}
```

---

## Fetch All Posts

**Endpoint:** `GET /api/posts`

**URL:** `http://localhost:8081/api/posts`

**Sample Response:**

```json
[
  {
    "id": 1,
    "title": "My First Blog",
    "content": "Spring Boot relational systems are powerful."
  }
]
```

---

# Web (Thymeleaf) Routes

| Route | Description |
|---|---|
| `GET /` | Home page — lists all blog posts |
| `GET /users/register` | User registration form |
| `GET /posts/new` | Create new post form |
| `GET /posts/edit/{id}` | Edit existing post |

*(Update these to match your actual `@Controller` routes)*

---

# Challenges & Solutions

During development, several real issues came up and were debugged and resolved.

---

## Issue 1: Tomcat Port Conflict (8080 already in use)

**Cause:** Another process was already running on port `8080`, preventing Tomcat from starting.

**Fix:** Changed the server port:

```properties
server.port=8081
```

---

## Issue 2: Infinite JSON Serialization Recursion (`StackOverflowError`)

**Cause:** Bidirectional `@OneToMany` / `@ManyToOne` mappings caused Jackson to recursively serialize related entities indefinitely.

**Fix:** Added `@JsonIgnore` on the back-reference field to break the recursive loop while preserving the ORM relationship.

---

## Issue 3: PostgreSQL Constraint Violation (`DataIntegrityViolationException`)

**Cause:** The database enforced a non-null constraint on `fullName`, but some API requests omitted this field.

**Fix:** Updated front-end forms, API request payloads, and validation logic to ensure required fields are always sent.

---

# Skills Demonstrated

- MVC architecture with Spring Boot
- Relational database design (PostgreSQL + Hibernate)
- RESTful API development
- Transaction management
- JSON serialization handling
- Thymeleaf server-side rendering
- Debugging and root-cause analysis

---

# Future Enhancements

- Spring Security + JWT Authentication
- Role-Based Access Control (RBAC)
- Docker support
- CI/CD pipeline
- Cloud deployment (AWS/GCP/Azure)
- Redis caching layer
- Real-time notifications via WebSocket
- Markdown blog editor
- Image upload support

---

# Author

Abhinav Devaradesi

Built as a full-stack portfolio project to practice Spring Boot, JPA/Hibernate, and Thymeleaf.
