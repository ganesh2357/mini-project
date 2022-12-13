package com.bg.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bg.entity.Booking;
import com.bg.exception.BusinessException;
import com.bg.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {
	@Autowired
	BookingRepository bookingRepo;

	@Override
	public String addRide(Booking ride) {

		if (ride.getLandRide().isEmpty()) {
			throw new BusinessException(HttpStatus.NOT_FOUND);
		}
		try {
			bookingRepo.save(ride);
			return "RIDE DETAILS ARE SAVED SUCCESSFULLY...";
		} catch (IllegalArgumentException e) {
			throw new BusinessException(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public String updateRide(Booking ride) {
		try {
			bookingRepo.save(ride);
			return "RIDE DETAILS ARE UPDATED SUCCESSFULLY...";
		} catch (IllegalArgumentException e) {
			throw new BusinessException(HttpStatus.BAD_REQUEST);
		}

	}

	public String deleteRideById(int id) {
		try {
			bookingRepo.deleteById(id);
			return "RIDE DETAILS ARE DELETED SUCCESSFULLY...";
		} catch (IllegalArgumentException e) {
			throw new BusinessException(HttpStatus.BAD_REQUEST);
		}

	}

	public List<Booking> getAllBookings() {
		List<Booking> bookings = null;
		try {
			bookings = bookingRepo.findAll();
		} catch (Exception e) {
			throw new BusinessException(HttpStatus.BAD_REQUEST);
		}
		return bookings;
	}

	@Override
	public Booking getBookingById(Integer id) {
		try {
			Optional<Booking> optional = bookingRepo.findById(id);
			Booking booking = optional.get();
			return booking;
		} catch (NoSuchElementException e) {
			throw new BusinessException(HttpStatus.NOT_FOUND);
		}

	}

}
