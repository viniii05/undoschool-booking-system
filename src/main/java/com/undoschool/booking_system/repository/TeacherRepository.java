package com.undoschool.booking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.undoschool.booking_system.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}