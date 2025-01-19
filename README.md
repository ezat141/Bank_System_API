# Bank System API

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.5-green)
![MySQL](https://img.shields.io/badge/MySQL-8.0-orange)
![Postman](https://img.shields.io/badge/Postman-API%20Testing-yellow)

A **Spring Boot-based RESTful API** for managing a banking system. This project allows users to register, authenticate, manage accounts, perform transactions (deposit/withdraw), and view transaction history.

---

## Features

- **User Management:**
  - Register new users.
  - Authenticate users using JWT (JSON Web Tokens).
  - Deactivate user accounts.

- **Account Management:**
  - Create new bank accounts.
  - Activate/deactivate accounts.
  - View account details and transaction history.

- **Transaction Management:**
  - Deposit funds into an account.
  - Withdraw funds from an account.
  - View transaction history for an account.

- **Security:**
  - JWT-based authentication.
  - Password encryption using BCrypt.
  - Role-based access control (Admin/User).

---

## Technologies Used

- **Backend:**
  - Java 17
  - Spring Boot 3.1.5
  - Spring Security
  - JWT (JSON Web Tokens)
  - MySQL Database
  - Hibernate (JPA)

- **Tools:**
  - Postman (API Testing)
  - Maven (Dependency Management)
  - Lombok (Reducing Boilerplate Code)

---

## Prerequisites

Before running the project, ensure you have the following installed:

- **Java Development Kit (JDK) 17**
- **MySQL Server** (or any other database of your choice)
- **Maven** (for dependency management)
- **Postman** (for API testing)

---

## Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/bank-system-api.git
cd bank-system-api
```

### 2. Configure the Database

- Create a MySQL database named `bank`.
- Update the `application.yml` file with your database credentials:

  ```yaml
  spring:
    datasource:
      url: jdbc:mysql://localhost:3306/bank
      username: your-username
      password: your-password
    jpa:
      hibernate:
        ddl-auto: update
  ```

### 3. Build and Run the Project

- Build the project using Maven:

  ```bash
  mvn clean install
  ```

- Run the application:

  ```bash
  mvn spring-boot:run
  ```

The application will start on `http://localhost:8080`.

---

## API Documentation

Explore our API endpoints using **Swagger UI**: [Swagger UI](http://localhost:8080/swagger-ui.html)

**Note:** Make sure your server is running to access the Swagger UI.

---

## API Endpoints

### Authentication

| **Endpoint**            | **Method** | **Description**                     |
|--------------------------|------------|-------------------------------------|
| `/bank/auth/register`    | POST       | Register a new user.                |
| `/bank/auth/authenticate`| POST       | Authenticate a user and get a JWT.  |

### User Management

| **Endpoint**            | **Method** | **Description**                     |
|--------------------------|------------|-------------------------------------|
| `/bank/user`             | GET        | Get the current user's profile.     |
| `/bank/user`             | PUT        | Deactivate the current user.        |
| `/bank/user/users`       | GET        | Get a list of all users (Admin).    |

### Account Management

| **Endpoint**                     | **Method** | **Description**                     |
|-----------------------------------|------------|-------------------------------------|
| `/bank/account`                   | POST       | Create a new account.               |
| `/bank/account`                   | GET        | Get all accounts for the user.      |
| `/bank/account/{accountId}`       | GET        | Get transaction history for an account. |
| `/bank/account/{cardNumber}?activate` | PUT    | Activate an account.                |
| `/bank/account/{cardNumber}?deactivate` | PUT  | Deactivate an account.              |

### Transaction Management

| **Endpoint**            | **Method** | **Description**                     |
|--------------------------|------------|-------------------------------------|
| `/bank/transaction/deposit` | POST    | Deposit funds into an account.      |
| `/bank/transaction/withdraw` | POST   | Withdraw funds from an account.     |

## Database Schema

### `User` Table
| Column        | Type          | Description                     |
|---------------|---------------|---------------------------------|
| `id`          | BIGINT        | Primary key.                    |
| `firstName`   | VARCHAR(255)  | User's first name.              |
| `lastName`    | VARCHAR(255)  | User's last name.               |
| `email`       | VARCHAR(255)  | User's email (unique).          |
| `password`    | VARCHAR(255)  | Encrypted password.             |
| `phoneNumber` | VARCHAR(11)   | User's phone number (unique).   |
| `address`     | VARCHAR(255)  | User's address.                 |
| `roleType`    | ENUM          | User's role (ADMIN/USER).       |
| `status`      | BOOLEAN       | User's account status.          |
| `createdAt`   | TIMESTAMP     | Account creation timestamp.     |

### `Account` Table
| Column        | Type          | Description                     |
|---------------|---------------|---------------------------------|
| `id`          | BIGINT        | Primary key.                    |
| `cardNumber`  | VARCHAR(16)   | Account's card number (unique). |
| `CVV`         | VARCHAR(4)    | Account's CVV.                  |
| `balance`     | DOUBLE        | Account balance.                |
| `status`      | BOOLEAN       | Account status.                 |
| `createdAt`   | TIMESTAMP     | Account creation timestamp.     |
| `user_id`     | BIGINT        | Foreign key to `User` table.    |

### `Transaction` Table
| Column            | Type          | Description                     |
|-------------------|---------------|---------------------------------|
| `id`              | BIGINT        | Primary key.                    |
| `transactionType` | ENUM          | Transaction type (DEPOSIT/WITHDRAW). |
| `amount`          | DOUBLE        | Transaction amount.             |
| `description`     | VARCHAR(255)  | Transaction description.        |
| `createdAt`       | TIMESTAMP     | Transaction timestamp.          |
| `account_id`      | BIGINT        | Foreign key to `Account` table. |

---

## Contributing

Contributions are welcome! If you'd like to contribute, please follow these steps:

1. Fork the repository.
2. Create a new branch for your feature/bugfix.
3. Commit your changes.
4. Push your branch and submit a pull request.
