# EquityRisk Platform

A financial risk management backend application built with Java 21, Spring Boot, and PostgreSQL.

The project focuses on backend engineering practices, REST API development, data persistence, validation, exception handling, and automated testing.

## Technologies

- Java 21
- Spring Boot 4.2
- Spring Data JPA
- Spring Web
- Bean Validation
- PostgreSQL 17
- Maven
- Docker and Docker Compose
- JUnit 5
- Mockito
- Git

## Architecture

The application follows a layered architecture:

```text
Controller
    |
    v
Service
    |
    v
Repository
    |
    v
PostgreSQL
```

**Main components:**

- **Controller:** Handles HTTP requests and responses.
- **Service:** Contains application and business logic.
- **Repository:** Manages database access through Spring Data JPA.
- **Entity:** Represents persistent domain data.
- **DTO:** Separates API request and response structures.
- **Exception Handler:** Centralizes API error handling.

## Implemented Features

- Transaction creation through REST API.
- Transaction retrieval by ID.
- Transaction listing with pagination.
- Request validation.
- Custom exception handling.
- Standardized error responses.
- PostgreSQL persistence.
- Unit and integration tests.

## API Endpoints

Base URL: `http://localhost:8080`

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/transactions` | Create a transaction |
| GET | `/api/transactions/{id}` | Retrieve a transaction |
| GET | `/api/transactions` | List transactions with pagination |

### Create a Transaction

**POST** `/api/transactions`

Request body:

```json
{
  "customerId": 15,
  "amount": 5000.00,
  "currency": "BRL"
}
```

### List Transactions

**GET** `/api/transactions?page=0&size=10`

Pagination parameters:

- `page`: Page index, starting at 0.
- `size`: Number of records per page (maximum 100).

## Error Handling

The API provides standardized error responses for situations such as:

- Resource not found (404).
- Invalid request parameters (400).
- Invalid request body (400).

Example:

```json
{
  "status": 404,
  "error": "Not Found",
  "message": "Transaction not found with ID: 999"
}
```

## Running Locally

### Prerequisites

- Java 21
- Docker Desktop
- Git

### 1. Clone the repository

```bash
git clone https://github.com/Igorlima101/equity-risk-platform.git
cd equity-risk-platform
```

### 2. Configure environment variables

Copy `.env.example` to `.env` and configure the database credentials.

### 3. Start PostgreSQL

```bash
docker compose up -d
```

### 4. Run the application

On Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

### 5. Run automated tests

On Windows:

```powershell
.\mvnw.cmd clean verify
```

## Testing

The project includes unit tests and integration tests using:

- JUnit 5
- Mockito
- Spring Boot Test
- MockMvc

Current test suite: 10 tests passing.

## Project Status

The project is under active development.

Future improvements may include:

- Transaction approval workflows.
- Credit risk assessment.
- Business rule enhancements.
- Additional automated tests.
- Improved API documentation.

## Purpose

This is an educational and portfolio project developed to demonstrate practical backend engineering skills, Java development, REST API design, database integration, software architecture, and automated testing.
