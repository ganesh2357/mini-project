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

import com.bg.entity.Booking;
import com.bg.service.BookingService;
import com.bg.util.AllConstants;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/booking")
public class BookingControl {
	@Autowired
	BookingService bookingService;

	private Logger logger = LoggerFactory.getLogger(BookingControl.class);

	// add rest api
	@PostMapping("/add")
	public ResponseEntity<?> addRide(@RequestBody Booking ride) {
		if (ride == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		try {
			bookingService.addRide(ride);
			logger.info("RIDE DETAILS ARE SAVED..");
			return new ResponseEntity<>(AllConstants.SUCCESS_MESSAGE, HttpStatus.CREATED);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// update rest api
	@PutMapping("/update")
	public ResponseEntity<?> updateRide(@RequestBody Booking ride) {
		try {
			bookingService.updateRide(ride);
			logger.info("RIDE DETAILS UPDATED..");
			return new ResponseEntity<>(AllConstants.SUCCESS_MESSAGE, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		}
	}

	// fetch all rest api
	@GetMapping("/getall")
	public ResponseEntity<?> fetchAll() {
		try {
			List<Booking> allBookings = bookingService.getAllBookings();
			logger.info("FETCHING ALL BOOKINGS..");
			return new ResponseEntity<>(allBookings, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getAllBookingsById(@PathVariable Integer id) {
		try {
			Booking rideList = bookingService.getBookingById(id);
			logger.info("Fetching all bookings..");
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
			bookingService.deleteRideById(id);
			logger.info("Deleting the booking...");
			return new ResponseEntity<>(AllConstants.SUCCESS_MESSAGE1, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

}
