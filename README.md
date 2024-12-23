
# Crop Management System API

A **Crop Management System** built with **Spring Boot** and **JWT** for backend security, and **HTML**, **CSS**, **JavaScript**, and **Bootstrap** for the frontend. This project supports managing crops, fields, equipment, logs, vehicles, and staff data efficiently.

## Features

- RESTful API endpoints for managing:
  - Crops
  - Fields
  - Equipment
  - Logs
  - Vehicles
  - Staff
- Secure access with **JWT-based authentication**.
- Responsive design with **Bootstrap**.
- Built as a **Gradle** project.

---

## Prerequisites

- **Java 17** or higher
- **Gradle** installed
- **Postman** or any API client for testing

---

## Project Setup

1. Clone the Frontend:  https://github.com/Thisura2001/Crop-monitoring-system-frontend.git
 

2. Run the application:
   ```bash
   ./gradlew bootRun
   ```

3. The server will be available at:
   ```
   http://localhost:9090
   ```

---

## API Endpoints

| Endpoint                        | Method | Description                           |
|---------------------------------|--------|---------------------------------------|
| `greenShadow/api/v1/crop`       | CRUD   | Manage crop data                     |
| `greenShadow/api/v1/field`      | CRUD   | Manage field data                    |
| `greenShadow/api/v1/equipment`  | CRUD   | Manage equipment data                |
| `greenShadow/api/v1/log`        | CRUD   | Manage logs                          |
| `greenShadow/api/v1/vehicle`    | CRUD   | Manage vehicles                      |
| `greenShadow/api/v1/staff`      | CRUD   | Manage staff                         |

### Example API Requests

- **GET Crops**
  ```http
  GET http://localhost:9090/greenShadow/api/v1/crop
  ```

- **POST New Crop**
  ```http
  POST http://localhost:9090/greenShadow/api/v1/crop
  Content-Type: application/json

  {
    "commonName": "Corn",
    "scientificName":"cron",
    "cropImg": "img",
    "season": "Spring",
    "field": "FIELD-1"
  }
  ```

---

## Security

- Authentication is secured with **JWT**.
- Generate a token by logging in via `/greenShadow/api/v1/auth/login`.
- Include the token in the `Authorization` header for authenticated requests:
  ```http
  Authorization: Bearer <your-jwt-token>
  ```

---

## Frontend

- The frontend files are located in the `src/main/resources/static` folder.
- Open the `index.html` file in your browser to access the application.

---

## Directory Structure

```
crop-management-system/
├── src/
│   ├── main/
│   │   ├── java/com/example/greenshadow
│   │   │   ├── controller/  # API Controllers
│   │   │   ├── service/     # Service Layer
│   │   │   ├── model/       # Data Models
│   │   │   ├── repository/  # JPA Repositories
│   │   │   ├── security/    # JWT Configuration
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/      # Frontend Files
│   └── test/                # Test Files
└── build.gradle             # Gradle Build Script
```

---

## Tools & Technologies

- **Backend:** Spring Boot, JWT, Hibernate
- **Frontend:** HTML, CSS, JavaScript, Bootstrap
- **Build Tool:** Gradle
- **Database:** MySQL (configured in `application.properties`)

---

## License

This project is licensed under the MIT License.

---

## Author

- **Thisura Liyanage**
  - 🌍 Agalawatta, Sri Lanka

--- 
