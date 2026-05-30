package com.undoschool.booking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.undoschool.booking_system.entity.Parent;

public interface ParentRepository extends JpaRepository<Parent, Long> {
}