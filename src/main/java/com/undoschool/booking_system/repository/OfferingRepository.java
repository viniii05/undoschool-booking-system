package com.undoschool.booking_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.undoschool.booking_system.entity.Offering;

public interface OfferingRepository extends JpaRepository<Offering, Long> {
	List<Offering> findByTeacherId(Long teacherId);
}