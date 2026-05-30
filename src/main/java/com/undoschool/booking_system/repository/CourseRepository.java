package com.undoschool.booking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.undoschool.booking_system.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}