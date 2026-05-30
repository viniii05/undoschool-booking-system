package com.undoschool.booking_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.undoschool.booking_system.dto.BookingRequest;
import com.undoschool.booking_system.entity.Booking;
import com.undoschool.booking_system.entity.Offering;
import com.undoschool.booking_system.service.ParentService;

@RestController
@RequestMapping("/parent")
public class ParentController {
	
	
	@Autowired
	private ParentService parentService;
	
	@GetMapping("/offerings")
	public List<Offering> getOfferings() {

	    return parentService.getAvailableOfferings();
	}
	
	@PostMapping("/bookings")
	public Booking bookOffering(
	        @RequestBody BookingRequest request) {

	    return parentService.bookOffering(
	            request);
	}
	
	@GetMapping("/{parentId}/bookings")
	public List<Booking> getBookings(
	        @PathVariable Long parentId) {

	    return parentService.getBookings(parentId);
	}
}
