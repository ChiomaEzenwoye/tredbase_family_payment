# TredBase Family Payment System

A secure, modular Spring Boot application that manages payments between parents and students. This system supports authentication with JWT, atomic balance updates, and H2 in-memory database for easy testing.

---

## 🚀 Features

- Parent & Student user models with balance tracking
- Secure JWT-based authentication
- RESTful endpoints for registration, login, and payments
- Atomic updates for balance management
- In-memory H2 database for development/testing

---

## 📦 Tech Stack

- Java 17+
- Spring Boot 3.x
- Spring Security
- H2 Database
- Maven
- Lombok

---

## 🛠️ Project Structure

```bash
ssrc/main/java/com/example/tred_base_test/
├── controller/       # REST controllers (Login, Register, Payment, etc.)
├── dto/              # Data Transfer Objects (e.g., request/response models)
├── model/            # Entities like User, Parent, Student
├── repo/             # Spring Data JPA Repositories
├── service/          # Interfaces for business logic
├── serviceImpl/      # Concrete implementations of service interfaces
├── security/         # JWT filters, token management
└── exceptionHandler/ # Custom exceptions
