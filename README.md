# Global Class Offering Booking System

Backend service for managing course offerings, sessions, and parent bookings.

## Tech Stack
- Java 17
- Spring Boot
- MySQL
- Spring Data JPA
- Maven

## Features

### Teacher APIs
- Create Offering
- Add Sessions
- Get Teacher Offerings

### Parent APIs
- View Available Offerings
- Book Offering
- View Bookings

### Advanced Features
- Timezone Conversion
- Conflict Detection
- Concurrency Handling using Pessimistic Locking

## API Endpoints

POST /teacher/offerings

POST /teacher/offerings/{offeringId}/sessions

GET /teacher/{teacherId}/offerings

GET /parent/offerings

POST /parent/bookings

GET /parent/{parentId}/bookings
