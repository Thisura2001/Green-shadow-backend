Crop Management System API
A comprehensive Crop Management System built using Spring Boot (Java) for the backend, JWT for security validations, and a frontend powered by HTML, CSS, JavaScript, and Bootstrap. This project enables efficient management of crops, fields, equipment, logs, vehicles, and staff.

Features
RESTful API endpoints for managing:
Crops
Fields
Equipment
Logs
Vehicles
Staff
Secure access with JWT-based authentication.
Responsive frontend using Bootstrap for styling.
Gradle-based project setup.
Prerequisites
Java 17 or above
Gradle installed
Spring Boot framework
A browser for testing the frontend
Postman or any API client for testing APIs
Project Setup
Clone the repository:

bash
Copy code
git clone https://github.com/your-username/crop-management-system.git
cd crop-management-system
Run the application:

bash
Copy code
./gradlew bootRun
The server will start at:

arduino
Copy code
http://localhost:9090
API Endpoints
Endpoint	Method	Description
greenShadow/api/v1/crop	CRUD	Manage crop data
greenShadow/api/v1/field	CRUD	Manage field data
greenShadow/api/v1/equipment	CRUD	Manage equipment data
greenShadow/api/v1/log	CRUD	Manage logs
greenShadow/api/v1/vehicle	CRUD	Manage vehicles
greenShadow/api/v1/staff	CRUD	Manage staff
Example API Requests
GET Crop List

http
Copy code
GET http://localhost:9090/greenShadow/api/v1/crop
POST New Crop

http
Copy code
POST http://localhost:9090/greenShadow/api/v1/crop
Content-Type: application/json

{
  "name": "Wheat",
  "fieldId": "F123",
  "season": "Winter",
  "harvestDate": "2024-12-15"
}
Security
All APIs are secured with JWT.
Generate a token by logging in with valid credentials at /api/v1/auth/login.
Include the token in the Authorization header for subsequent requests:
http
Copy code
Authorization: Bearer <your-jwt-token>
Frontend
The frontend is served separately, leveraging HTML, CSS, and Bootstrap for responsive design.
Access the frontend by opening the index.html file in your browser.
Directory Structure
bash
Copy code
crop-management-system/
├── src/
│   ├── main/
│   │   ├── java/com/example/cropmanagement
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
Tools & Technologies
Backend: Spring Boot, JWT, Hibernate
Frontend: HTML, CSS, JavaScript, Bootstrap
Build Tool: Gradle
Database: H2/PostgreSQL/MySQL (configure in application.properties)
Contributing
Fork the repository.
Create a feature branch:
bash
Copy code
git checkout -b feature-name
Commit your changes:
bash
Copy code
git commit -m "Add feature name"
Push to the branch:
bash
Copy code
git push origin feature-name
Create a pull request.
License
This project is licensed under the MIT License.

Author
Thisura Liyanage
🌍 Agalawatta, Sri Lanka
✉️ Email: thisuravimukthi123@gmail.com
