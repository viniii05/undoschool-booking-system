package com.undoschool.booking_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.undoschool.booking_system.entity.Session;

public interface SessionRepository extends JpaRepository<Session, Long> {

    List<Session> findByOfferingId(Long offeringId);
}