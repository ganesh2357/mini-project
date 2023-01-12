package com.bg.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bg.dto.BookingDto;
import com.bg.entity.Booking;
import com.bg.exception.BusinessException;
import com.bg.repository.BookingRepository;
import com.bg.util.AllConstants;

@Service
public class BookingServiceImpl implements BookingService {
	@Autowired
	BookingRepository bookingRepo;
	@Autowired
	ModelMapper modelmap;

	@Override
	public String addRide(Booking ride) {
		try {
			bookingRepo.save(ride);
			return AllConstants.RIDER_SUCCESS_MESSAGE;
		} catch (IllegalArgumentException e) {
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public String updateRide(Booking ride) {
		
		try {
			bookingRepo.save(ride);
			return AllConstants.RIDER_SUCCESS_MESSAGE1;
		} catch (IllegalArgumentException e) {
			throw new BusinessException(HttpStatus.BAD_REQUEST);
		}

	}

	@Override
	public String deleteRideById(Integer id) {
		try {
			bookingRepo.deleteById(id);
			return AllConstants.RIDER_SUCCESS_MESSAGE2;
		} catch (IllegalArgumentException e) {
			throw new BusinessException(HttpStatus.BAD_REQUEST);
		}

	}

	@Override
	public List<BookingDto> getAllBookings() {
		List<Booking> bookings = null;
		try {
			bookings = bookingRepo.findAll();
		} catch (Exception e) {
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return bookings.stream().map(booking -> 
					modelmap.map(booking, BookingDto.class))
					.collect(Collectors.toList());
	}

	@Override
	public BookingDto getBookingById(Integer id) {
		Booking booking = null;
		try {
			Optional<Booking> optional = bookingRepo.findById(id);
			booking = optional.get();

		} catch (NoSuchElementException e) {
			throw new BusinessException(HttpStatus.NOT_FOUND);
		}
		return modelmap.map(booking, BookingDto.class);
	}

}
