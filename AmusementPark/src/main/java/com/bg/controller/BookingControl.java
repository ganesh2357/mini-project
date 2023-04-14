package com.bg.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bg.dto.BookingDto;
import com.bg.service.BookingService;

@RestController
@CrossOrigin("*")
@RequestMapping("/booking")
public class BookingControl {
	@Autowired
	BookingService bookingService;

	private Logger logger = LoggerFactory.getLogger(BookingControl.class);

	// add rest api
	@PostMapping("/add")
	public ResponseEntity<String> addBooking(@RequestBody BookingDto bookingDto) {
		String response = null;
		try {
			if (bookingDto == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			} else {
				response = bookingService.addBooking(bookingDto);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	// update rest api
	@PutMapping("/update")
	public ResponseEntity<?> updateBooking(@RequestBody BookingDto bookingDto) {
		try {
			String response = bookingService.updateBooking(bookingDto);
			return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		}
	}

	// fetch all rest api
	@GetMapping("/getall")
	public ResponseEntity<?> fetchAll() {
		try {
			List<BookingDto> allBookings = bookingService.getAllBookings();
			if (allBookings == null || allBookings.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(allBookings, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getAllBookingsById(@PathVariable Integer id) {
		try {
			BookingDto rideList = bookingService.getBookingById(id);
			return new ResponseEntity<>(rideList, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		}
	}

	// delete booking by id
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteRideById(@PathVariable Integer id) {
		if (id == 0 || id < 1) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		try {
			String string = bookingService.deleteBookingById(id);
			return new ResponseEntity<>(string, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

}
