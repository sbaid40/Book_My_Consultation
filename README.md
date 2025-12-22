# 📋 BookMyConsultation

> A comprehensive microservices-based platform for booking doctor consultations with real-time notifications, secure payments, and intelligent rating systems.

![Architecture](https://img.shields.io/badge/Architecture-Microservices-blue?style=flat-square)
![Language](https://img.shields.io/badge/Language-Java-orange?style=flat-square)
![Framework](https://img.shields.io/badge/Framework-Spring%20Boot-green?style=flat-square)
![Docker](https://img.shields.io/badge/Platform-Docker-lightblue?style=flat-square)

---

## 🌟 Overview

**BookMyConsultation** is an enterprise-grade microservices platform designed to simplify the process of booking doctor consultations. The system leverages cutting-edge cloud-native technologies to provide a scalable, reliable, and secure solution for patients and healthcare providers.

### Key Features

- ✅ **User Management** - Register and manage patient profiles with MongoDB
- ✅ **Doctor Directory** - Browse and search doctors with detailed profiles
- ✅ **Appointment Booking** - Schedule and manage appointments with real-time availability
- ✅ **Secure Payments** - Process payments safely with integrated payment gateway
- ✅ **Rating & Reviews** - Rate consultations and share feedback
- ✅ **Notifications** - Real-time email/SMS notifications for appointments
- ✅ **Service Discovery** - Eureka-based service registration and discovery
- ✅ **API Gateway** - Centralized gateway for routing requests
- ✅ **Message Streaming** - Apache Kafka for async event processing
- ✅ **Distributed Tracing** - Spring Cloud Sleuth for request tracking

---

## 🏗️ System Architecture

### Microservices Overview

The platform is built with **9 independent microservices**, each responsible for specific business domains:

```
┌─────────────────────────────────────────────────────────┐
│                    API Gateway (9191)                   │
│         (Spring Cloud Gateway + Eureka Client)          │
└─────────────────────────────────────────────────────────┘
                             │
        ┌────────────────────┼────────────────────┐
        │                    │                    │
        ▼                    ▼                    ▼
   ┌──────────┐         ┌──────────┐        ┌──────────┐
   │Auth Svc  │         │ User Svc │        │ Doc Svc  │
   │  (8081)  │         │ (8082)   │        │ (8083)   │
   └──────────┘         └──────────┘        └──────────┘
        │                    │                    │
        └────────────────────┼────────────────────┘
                             │
        ┌────────────────────┼────────────────────┐
        │                    │                    │
        ▼                    ▼                    ▼
   ┌─────────────┐    ┌──────────────┐    ┌──────────┐
   │Appointment  │    │ Payment Svc  │    │ Rating   │
   │  (8086)     │    │  (8084)      │    │ (8085)   │
   └─────────────┘    └──────────────┘    └──────────┘
        │                    │                    │
        └────────────────────┼────────────────────┘
                             │
        ┌────────────────────┼────────────────────┐
        │                    │
        ▼                    ▼
   ┌──────────────┐    ┌──────────────┐
   │Notification  │    │Eureka Server │
   │  (8080)      │    │  (8761)      │
   └──────────────┘    └──────────────┘
        │
    ┌───┴───┬────────┬────────┐
    ▼       ▼        ▼        ▼
  Kafka  MySQL   MongoDB  (Message Queue)
```

### Service Details

| Service | Port | Purpose | Key Tech |
|---------|------|---------|----------|
| **Eureka Service** | 8761 | Service Registry & Discovery | Spring Cloud Eureka |
| **API Gateway** | 9191 | Request Routing & Load Balancing | Spring Cloud Gateway |
| **Auth Service** | 8081 | Authentication & Authorization | Spring Security |
| **User Service** | 8082 | User Profile Management | Spring Data MongoDB |
| **Doctor Service** | 8083 | Doctor Directory & Management | Spring Data JPA, MySQL |
| **Appointment Service** | 8086 | Booking & Scheduling | Spring Data JPA, Kafka |
| **Payment Service** | 8084 | Payment Processing | Payment Gateway Integration |
| **Rating Service** | 8085 | Reviews & Ratings | Spring Data JPA |
| **Notification Service** | 8080 | Email/SMS Notifications | Kafka Consumer |

---

## 🛠️ Tech Stack

### Backend
- **Language:** Java 11+
- **Framework:** Spring Boot 2.4.5 - 2.6.4
- **Cloud:** Spring Cloud (2020.0.2, 2021.0.1)
- **API Gateway:** Spring Cloud Gateway
- **Service Discovery:** Netflix Eureka
- **Message Queue:** Apache Kafka

### Databases
- **Relational:** MySQL (Appointments, Payments, Ratings, Doctors)
- **NoSQL:** MongoDB (User Profiles, Caching)

### Infrastructure
- **Containerization:** Docker
- **Orchestration:** Docker Compose
- **Monitoring:** Spring Cloud Sleuth (Distributed Tracing)
- **Logging:** Spring Boot Logging

### Dependencies
- Spring Data JPA & MongoDB
- Spring Kafka
- Spring Cloud Sleuth
- MySQL Connector Java
- ModelMapper
- AWS Java SDK

---

## 🚀 Quick Start

### Prerequisites

- **Java:** JDK 11 or higher
- **Maven:** 3.6.0 or higher
- **Docker:** 20.10+
- **Docker Compose:** 1.29+

### Installation & Setup

#### Option 1: Docker Compose (Recommended)

```bash
# Clone the repository
git clone https://github.com/yourusername/BookMyConsultation.git
cd BookMyConsultation

# Start all services with Docker Compose
docker-compose up -d

# Check service status
docker-compose ps
```

#### Option 2: Build from Source

```bash
# Clone the repository
git clone https://github.com/yourusername/BookMyConsultation.git
cd BookMyConsultation

# Build the entire project
mvn clean install -DskipTests

# Start Eureka Service first
cd eureka-service
mvn spring-boot:run

# In separate terminals, start other services
cd ../api-gateway && mvn spring-boot:run
cd ../auth-service && mvn spring-boot:run
cd ../user-service && mvn spring-boot:run
cd ../doc-service && mvn spring-boot:run
cd ../appointment-service && mvn spring-boot:run
cd ../payment-service && mvn spring-boot:run
cd ../rating-service && mvn spring-boot:run
cd ../notification-service && mvn spring-boot:run
```

### Verify Installation

Once all services are running:

```bash
# Check Eureka Dashboard
curl http://localhost:8761/

# Test API Gateway
curl http://localhost:9191/health

# List registered services
curl http://localhost:8761/eureka/apps
```

---

## 📡 API Endpoints

### Authentication Service (Port 8081)
```
POST   /auth/login          - User login
POST   /auth/register       - User registration
POST   /auth/refresh        - Refresh token
POST   /auth/logout         - User logout
```

### User Service (Port 8082)
```
GET    /users/{userId}      - Get user profile
PUT    /users/{userId}      - Update user profile
DELETE /users/{userId}      - Delete user
GET    /users/search        - Search users
```

### Doctor Service (Port 8083)
```
GET    /doctors             - List all doctors
GET    /doctors/{docId}     - Get doctor details
POST   /doctors             - Add new doctor (Admin)
PUT    /doctors/{docId}     - Update doctor info
DELETE /doctors/{docId}     - Delete doctor (Admin)
GET    /doctors/search      - Search doctors by specialty
```

### Appointment Service (Port 8086)
```
POST   /appointments        - Book appointment
GET    /appointments/{id}   - Get appointment details
GET    /appointments        - List user appointments
PUT    /appointments/{id}   - Update appointment
DELETE /appointments/{id}   - Cancel appointment
GET    /appointments/availability - Check doctor availability
```

### Payment Service (Port 8084)
```
POST   /payments            - Process payment
GET    /payments/{id}       - Get payment details
GET    /payments/status/{id} - Check payment status
POST   /payments/refund     - Request refund
```

### Rating Service (Port 8085)
```
POST   /ratings             - Add rating/review
GET    /ratings/{docId}     - Get doctor ratings
PUT    /ratings/{ratingId}  - Update rating
DELETE /ratings/{ratingId}  - Delete rating
GET    /ratings/average/{docId} - Get average rating
```

### Notification Service (Port 8080)
```
POST   /notifications       - Send notification
GET    /notifications/{userId} - Get user notifications
PUT    /notifications/{id}  - Mark as read
```

---

## 🔄 Data Flow & Communication

### Service-to-Service Communication
- **Synchronous:** REST APIs via API Gateway
- **Asynchronous:** Apache Kafka for event-driven architecture

### Key Event Topics (Kafka)
- `appointment-booked` - Triggered when appointment is scheduled
- `payment-processed` - Triggered after successful payment
- `rating-submitted` - Triggered when user submits a rating
- `notification-required` - Triggers notification service

### Example Booking Flow

```
1. User → API Gateway → Appointment Service
   ├─ Create Appointment
   ├─ Publish "appointment-booked" event to Kafka
   └─ Return appointment ID

2. Notification Service (Kafka Consumer)
   ├─ Listen to "appointment-booked"
   ├─ Fetch user & doctor details
   ├─ Send Email/SMS notification
   └─ Update notification status

3. Appointment Service (Kafka Consumer)
   ├─ Listen to "payment-processed"
   ├─ Update appointment status to "confirmed"
   └─ Notify user of confirmation
```

---

## 🔐 Security Features

- **Authentication:** JWT-based token authentication
- **Authorization:** Role-based access control (RBAC)
- **API Gateway:** Request validation and rate limiting
- **Encrypted Communication:** HTTPS support
- **Database Security:** Connection pooling and credential management
- **Audit Logging:** Request/response tracking via Spring Cloud Sleuth

---

## 📊 Database Schema

### Key Entities

**Users (MongoDB)**
```json
{
  "_id": "ObjectId",
  "email": "user@example.com",
  "firstName": "John",
  "lastName": "Doe",
  "phone": "1234567890",
  "avatar": "url",
  "createdAt": "2024-01-01",
  "updatedAt": "2024-01-15"
}
```

**Doctors (MySQL)**
```sql
CREATE TABLE doctors (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255),
  specialty VARCHAR(100),
  qualification VARCHAR(255),
  experience INT,
  consultationFee DECIMAL(10, 2),
  availableSlots JSON,
  rating DECIMAL(3, 2),
  createdAt TIMESTAMP
);
```

**Appointments (MySQL)**
```sql
CREATE TABLE appointments (
  id INT PRIMARY KEY AUTO_INCREMENT,
  userId INT,
  doctorId INT,
  appointmentDate DATE,
  appointmentTime TIME,
  status VARCHAR(50),
  notes TEXT,
  createdAt TIMESTAMP,
  FOREIGN KEY (userId) REFERENCES users(id),
  FOREIGN KEY (doctorId) REFERENCES doctors(id)
);
```

---

## 🧪 Testing

### Run Unit Tests

```bash
# Test all services
mvn test

# Test specific service
cd user-service && mvn test

# Run with coverage
mvn clean test jacoco:report
```

### Integration Tests

```bash
# Start services
docker-compose up -d

# Run integration tests
mvn verify -DskipUnitTests
```

---

## 📈 Monitoring & Logging

### Distributed Tracing

The platform uses **Spring Cloud Sleuth** to track requests across services:

```bash
# View traces in logs
docker logs notification-service | grep "trace"

# Check service correlation IDs
curl http://localhost:8082/users/1 -v | grep X-Trace-ID
```

### Log Aggregation

Logs from all services can be aggregated using:
- ELK Stack (Elasticsearch, Logstash, Kibana)
- Splunk
- Datadog

### Health Checks

```bash
# Check individual service health
curl http://localhost:8082/actuator/health
curl http://localhost:8083/actuator/health

# Check all services via API Gateway
curl http://localhost:9191/health
```

---

## 🚀 Deployment

### Docker Build

```bash
# Build all images
docker-compose build

# Push to Docker Registry
docker tag bookmyconsultation/user-service:1.0.0 your-registry/user-service:1.0.0
docker push your-registry/user-service:1.0.0
```

### Kubernetes Deployment (Optional)

Create Kubernetes manifests for production deployment:

```bash
# Example: Create ConfigMap for application properties
kubectl create configmap app-config --from-file=application.properties

# Deploy services
kubectl apply -f k8s/
```

---

## 🤝 Contributing

We welcome contributions! Please follow these steps:

1. **Fork** the repository
2. **Create** a feature branch (`git checkout -b feature/amazing-feature`)
3. **Commit** your changes (`git commit -m 'Add amazing feature'`)
4. **Push** to the branch (`git push origin feature/amazing-feature`)
5. **Open** a Pull Request

### Code Standards
- Follow Google Java Style Guide
- Write unit tests for new features
- Add JavaDoc comments for public APIs
- Ensure code passes linting checks

---

## 📋 Project Structure

```
BookMyConsultation/
├── api-gateway/              # API Gateway Service
│   ├── src/main/java/
│   ├── src/test/java/
│   └── pom.xml
├── auth-service/             # Authentication Service
├── user-service/             # User Management Service
├── doc-service/              # Doctor Directory Service
├── appointment-service/      # Appointment Booking Service
├── payment-service/          # Payment Processing Service
├── rating-service/           # Rating & Review Service
├── notification-service/     # Notification Service
├── eureka-service/           # Service Registry
├── docker-compose.yml        # Docker Compose Configuration
├── pom.xml                   # Parent POM
├── CodeLogic.pdf             # System Design Document
└── README.md                 # This file
```

---

## 🐛 Troubleshooting

### Service Not Registering with Eureka

```bash
# Check Eureka logs
docker logs eureka-service

# Verify service configuration
curl http://localhost:8761/eureka/apps/{service-name}
```

### Database Connection Issues

```bash
# Check database containers
docker-compose ps | grep database

# View database logs
docker-compose logs database

# Verify credentials in application.properties
```

### Kafka Connection Errors

```bash
# Check Kafka broker status
docker logs kafka

# List topics
docker exec kafka kafka-topics --list --bootstrap-server localhost:9092

# Create missing topic
docker exec kafka kafka-topics --create --topic appointment-booked --bootstrap-server localhost:9092
```

---

## 📚 Additional Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Cloud Documentation](https://spring.io/projects/spring-cloud)
- [Apache Kafka Documentation](https://kafka.apache.org/documentation/)
- [Docker Documentation](https://docs.docker.com/)
- [CodeLogic.pdf](./CodeLogic.pdf) - Detailed system design and business logic

---

## 📄 License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

---

## 👥 Authors & Contributors

- **Upgrad** - Initial Development
- **Contributors:** [Add your name here]

---

## 📞 Support

For issues, questions, or suggestions:

- **Issues:** [GitHub Issues](https://github.com/yourusername/BookMyConsultation/issues)
- **Email:** support@bookmyconsultation.com
- **Documentation:** [Wiki](https://github.com/yourusername/BookMyConsultation/wiki)

---

## 🎯 Future Enhancements

- [ ] Video consultation integration
- [ ] Mobile app (iOS & Android)
- [ ] AI-powered doctor recommendation
- [ ] Telemedicine support
- [ ] Prescription management
- [ ] Health records integration
- [ ] Multi-language support
- [ ] Advanced analytics dashboard

---

<div align="center">

**Made with ❤️ by Upgrad**

⭐ If you find this project helpful, please star it!

</div>

