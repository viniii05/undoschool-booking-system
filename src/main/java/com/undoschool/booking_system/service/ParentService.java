package com.undoschool.booking_system.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.undoschool.booking_system.dto.BookingRequest;
import com.undoschool.booking_system.entity.Booking;
import com.undoschool.booking_system.entity.Offering;
import com.undoschool.booking_system.entity.Parent;
import com.undoschool.booking_system.entity.Session;
import com.undoschool.booking_system.repository.BookingRepository;
import com.undoschool.booking_system.repository.OfferingRepository;
import com.undoschool.booking_system.repository.ParentRepository;
import com.undoschool.booking_system.repository.SessionRepository;

import jakarta.transaction.Transactional;

@Service
public class ParentService {
	@Autowired
	private OfferingRepository offeringRepository;
	
	@Autowired
	private ParentRepository parentRepository;

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private SessionRepository sessionRepository;

	private boolean overlap(
	        Instant s1,
	        Instant e1,
	        Instant s2,
	        Instant e2) {

	    return s1.isBefore(e2)
	            && s2.isBefore(e1);
	}
	public List<Offering> getAvailableOfferings() {

	    return offeringRepository.findAll();
	}
	
	@Transactional
	public Booking bookOffering(BookingRequest request) {

	    bookingRepository.lockBookings(
	            request.getParentId());

	    Parent parent =
	            parentRepository.findById(
	                    request.getParentId())
	                    .orElseThrow();

	    Offering offering =
	            offeringRepository.findById(
	                    request.getOfferingId())
	                    .orElseThrow();

	    List<Session> requestedSessions =
	            sessionRepository.findByOfferingId(
	                    offering.getId());

	    List<Booking> existingBookings =
	            bookingRepository.findByParentId(
	                    parent.getId());

	    for (Booking booking : existingBookings) {

	        List<Session> bookedSessions =
	                sessionRepository.findByOfferingId(
	                        booking.getOffering().getId());

	        for (Session requested : requestedSessions) {

	            for (Session booked : bookedSessions) {

	                if (overlap(
	                        requested.getStartTime(),
	                        requested.getEndTime(),
	                        booked.getStartTime(),
	                        booked.getEndTime())) {

	                    throw new RuntimeException(
	                            "Time conflict detected");
	                }
	            }
	        }
	    }

	    Booking newBooking = new Booking();

	    newBooking.setParent(parent);
	    newBooking.setOffering(offering);
	    newBooking.setBookedAt(
	            LocalDateTime.now());

	    return bookingRepository.save(
	            newBooking);
	}
	public List<Booking> getBookings(Long parentId) {

	    return bookingRepository.findByParentId(parentId);
	}
}
