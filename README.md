# 🚀 Blog4U Live: Enterprise Relational Blogging Platform

A high-performance, full-stack MVC blogging application engineered using **Java 21**, **Spring Boot 4.x**, and **PostgreSQL**.

This platform provides a complete content management lifecycle featuring:

- Layered architectural isolation
- Strict data validation
- Automated relational schema tracking
- Dynamic server-side rendering powered by Thymeleaf

---

# System Architecture & Framework Matrix

The system maps clean visual template pipelines seamlessly into a decoupled transactional database tier using the following enterprise stack:

| Layer | Technology |
|---|---|
| Back-End Engine | Spring Boot 4.0.6 & Java 21 (JDK 21) |
| Data Access Tier | Spring Data JPA & Hibernate ORM 7.2 |
| Relational Database | PostgreSQL 18.3 with HikariCP |
| Front-End Render Engine | Thymeleaf + Bootstrap 5.3 CDN |
| Development Tooling | IntelliJ IDEA 2026.1 & Postman |

---

# Core Platform Capabilities

## 1. Layered Decoupled Routing

Implements enterprise-grade separation between:

- REST APIs using `@RestController`
- Server-rendered view pipelines using `@Controller`

---

## 2. Managed Relational Mapping

Leverages strong database entity relationships where:

- One `User` maps to many `Post` records
- Cascade lifecycle operations are enabled using `CascadeType.ALL`
- Serialization recursion prevention is handled via `@JsonIgnore`

---

## 3. Robust Transaction Isolation

Database operations are wrapped inside transactional service layers to ensure:

- Safe rollback protection
- Atomic database consistency
- Isolation between concurrent operations

---

## 4. Full CRUD Interactivity

Supports live browser-driven functionality including:

- Registering authors
- Publishing blog posts
- Editing records
- Updating entities
- Cascade deleting linked articles

---

# 🛠️ Step-by-Step Local Deployment Guide

Follow the steps below to deploy the platform locally.

---

## 1. Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/blog-platform.git
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
# Network Endpoint Assignment
server.port=8081

# PostgreSQL Data Source Sync Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/blogdb?createDatabaseIfNotExist=true
spring.datasource.username=postgres
spring.datasource.password=YOUR_POSTGRES_PASSWORD_HERE
spring.datasource.driver-class-name=org.postgresql.Driver

# Hibernate Engine Behavioral Tunings
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.open-in-view=false
```

---

## 4. Build & Run the Application

Execute the Maven lifecycle commands:

```bash
mvn clean install
mvn spring-boot:run
```

### IntelliJ IDEA Alternative

Run the application directly by launching:

```text
BlogPlatformApplication.java
```

using the green Run button.

---

## 5. Access the Platform

Once Tomcat starts successfully, open:

```text
http://localhost:8081/
```

---

# API Endpoint Evaluation Documentation

You can test all API endpoints using Postman.

---

## Register New User

### Endpoint

```http
POST /api/users
```

### URL

```text
http://localhost:8081/api/users
```

### Sample JSON

```json
{
  "fullName": "John Doe",
  "username": "johndoe",
  "email": "john@example.com"
}
```

---

## Fetch All Users

### Endpoint

```http
GET /api/users
```

### URL

```text
http://localhost:8081/api/users
```

---

## Create Post for User

### Endpoint

```http
POST /api/posts/user/{id}
```

### Example

```text
http://localhost:8081/api/posts/user/1
```

### Sample JSON

```json
{
  "title": "My First Blog",
  "content": "Spring Boot relational systems are powerful."
}
```

---

## Fetch All Posts

### Endpoint

```http
GET /api/posts
```

### URL

```text
http://localhost:8081/api/posts
```

---

# 🛠️ Critical Technical Root Cause Assessments & Bug Remediation

During development, multiple real-world enterprise engineering failures were encountered and resolved.

---

# Incident 1: Tomcat Bind Failure (Port 8080 Conflict)

## Root Cause Analysis

A background process or another application already occupied port `8080`, preventing Tomcat from starting.

## Remediation Strategy

Shifted the server runtime to:

```properties
server.port=8081
```

This bypassed the socket collision and restored successful application startup.

---

# Incident 2: Infinite JSON Serialization Recursion (`StackOverflowError`)

## Root Cause Analysis

Bidirectional entity mappings using:

- `@OneToMany`
- `@ManyToOne`

caused Jackson serialization to recursively loop between entities indefinitely.

## Remediation Strategy

Implemented:

```java
@JsonIgnore
```

on relational reference fields to prevent cyclic serialization while preserving ORM linkage integrity.

---

# Incident 3: PostgreSQL Constraint Violation (`DataIntegrityViolationException`)

## Root Cause Analysis

The database enforced non-null constraints on the `fullName` field, but incoming API payloads omitted the required property.

## Remediation Strategy

Updated:

- Front-end forms
- API request payloads
- Validation structures

to guarantee mandatory fields were always transmitted correctly.

---

# Enterprise Engineering Highlights

This project demonstrates practical experience with:

- Enterprise MVC architecture
- Relational database engineering
- Hibernate ORM lifecycle management
- RESTful API development
- Transaction management
- JSON serialization control
- PostgreSQL schema enforcement
- Full-stack Spring Boot development
- Thymeleaf server-side rendering
- Production debugging & root-cause analysis

---

# Future Enhancements

Potential future upgrades include:

- Spring Security + JWT Authentication
- Role-Based Access Control (RBAC)
- Dockerized Deployment
- CI/CD Integration
- Cloud Hosting (AWS/GCP/Azure)
- Redis Caching Layer
- Elasticsearch Integration
- Real-Time Notifications via WebSocket
- Markdown Blog Editor
- Image Upload Support

---

# 👨‍💻 Author
Abhinav Devaradesi

Developed as an enterprise-grade relational blogging platform portfolio project using modern Java ecosystem technologies.

---
