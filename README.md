# Global Class Offering Booking System

A backend service built for managing global live-learning classes where teachers can create course offerings and parents/students can book them across different time zones.

This project was developed as part of the Backend Engineering Assessment for Undoschool.

---

## Overview

In a global learning platform, teachers may conduct classes from different countries and time zones, while parents and students access schedules from their own local regions.

This system allows:

- Teachers to create course offerings and sessions
- Parents to browse available offerings
- Parents to book complete offerings
- Automatic prevention of overlapping bookings
- Proper timezone-aware scheduling
- Safe concurrent booking handling

---

## Tech Stack

- Java 17
- Spring Boot 3.5
- Spring Data JPA
- MySQL
- Maven
- Swagger OpenAPI

---

## Project Architecture

The application follows a layered architecture:

```
Controller Layer
        ↓
Service Layer
        ↓
Repository Layer
        ↓
MySQL Database
```

### Packages

```
com.undoschool.booking_system

├── controller
├── service
├── repository
├── entity
├── dto
├── exception
└── BookingSystemApplication
```

---

## Database Design

### Course

Stores course information.

| Field | Type |
|---------|---------|
| id | Long |
| name | String |
| description | String |

---

### Teacher

Stores teacher information.

| Field | Type |
|---------|---------|
| id | Long |
| name | String |
| timezone | String |

---

### Parent

Stores parent/student information.

| Field | Type |
|---------|---------|
| id | Long |
| name | String |
| timezone | String |

---

### Offering

Represents a schedulable version of a course.

| Field | Type |
|---------|---------|
| id | Long |
| name | String |
| course_id | FK |
| teacher_id | FK |

---

### Session

Stores actual class timings.

| Field | Type |
|---------|---------|
| id | Long |
| offering_id | FK |
| start_time | UTC Timestamp |
| end_time | UTC Timestamp |

---

### Booking

Stores parent bookings.

| Field | Type |
|---------|---------|
| id | Long |
| parent_id | FK |
| offering_id | FK |
| booked_at | Timestamp |

---

## Implemented APIs

### Teacher APIs

#### Create Offering

```http
POST /teacher/offerings
```

Creates a new offering for a course.

---

#### Add Session

```http
POST /teacher/offerings/{offeringId}/sessions
```

Adds a session to an offering.

---

#### Get Teacher Offerings

```http
GET /teacher/{teacherId}/offerings
```

Returns all offerings created by a teacher.

---

## Parent APIs

### Get Available Offerings

```http
GET /parent/offerings
```

Returns all available offerings.

---

### Book Offering

```http
POST /parent/bookings
```

Books an entire offering for a parent.

---

### Get Parent Bookings

```http
GET /parent/{parentId}/bookings
```

Returns all bookings made by a parent.

---

## Timezone Handling

Teachers create sessions in their local timezone.

Before storing, session times are converted into UTC and saved in the database.

Example:

Teacher Timezone:

```
America/New_York
```

Teacher creates session:

```
06:00 PM - 07:00 PM
```

Stored in database:

```
10:00 PM - 11:00 PM UTC
```

This ensures consistent scheduling across different regions.

---

## Booking Conflict Detection

Bookings are made at the offering level.

When a parent attempts to book an offering:

1. All sessions belonging to the offering are fetched.
2. Existing booked sessions for that parent are fetched.
3. Every session is checked for overlap.
4. Booking is rejected if any conflict exists.

Example:

### Already Booked

```
Saturday Batch
06:00 PM - 07:00 PM
```

### New Booking Attempt

```
Sunday Batch
06:30 PM - 07:30 PM
```

Result:

```
Booking Rejected
Time conflict detected
```

---

## Concurrency Handling

To prevent race conditions during simultaneous booking requests:

- `@Transactional` is used
- `PESSIMISTIC_WRITE` locking is applied

This guarantees:

- Data consistency
- Safe concurrent access
- Prevention of duplicate or invalid bookings

---

## Error Handling

Global exception handling is implemented using:

```java
@RestControllerAdvice
```

Custom Exceptions:

- ResourceNotFoundException
- ConflictException

Appropriate HTTP status codes are returned for failures.

---

## Running the Project

### Clone Repository

```bash
git clone https://github.com/viniii05/undoschool-booking-system.git
```

### Configure MySQL

Create database:

```sql
CREATE DATABASE undoschool;
```

Update:

```properties
application.properties
```

with your MySQL credentials.

---

### Run Application

```bash
mvn spring-boot:run
```

Application starts on:

```
http://localhost:8080
```

---

## Swagger Documentation

After running the application:

```
http://localhost:8080/swagger-ui/index.html
```

---

## Future Improvements

- JWT Authentication
- Role-based Authorization
- Session Capacity Management
- Booking Cancellation
- Pagination & Filtering
- Docker Deployment

---

## Assignment Highlights

✔ Clean layered architecture

✔ Timezone-aware scheduling

✔ Conflict detection logic

✔ Concurrent booking handling

✔ RESTful API design

✔ MySQL relational database design

✔ Global exception handling

✔ Swagger API documentation

---

## Author

**Vini Tamrakar**

Backend Engineering Assessment Submission for **Undoschool**
