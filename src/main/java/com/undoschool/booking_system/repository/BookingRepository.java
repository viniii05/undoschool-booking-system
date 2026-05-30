package com.undoschool.booking_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.undoschool.booking_system.entity.Booking;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;


public interface BookingRepository extends JpaRepository<Booking, Long> {

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("""
	SELECT b
	FROM Booking b
	WHERE b.parent.id = :parentId
	""")
	List<Booking> lockBookings(Long parentId);
    List<Booking> findByParentId(Long parentId);
}