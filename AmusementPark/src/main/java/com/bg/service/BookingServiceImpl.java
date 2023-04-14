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
	public String addBooking(BookingDto bookingDto) {

		try {
			Booking booking = convertToEntity(bookingDto);
			booking = bookingRepo.save(booking);

			if (booking.getId() != null) {
				return AllConstants.RIDER_SUCCESS_MESSAGE;
			} else {
				return AllConstants.ERROR_MESSAGE3;
			}
		} catch (IllegalArgumentException e) {
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public String updateBooking(BookingDto bookingDto) {
		try {

			Booking booking = convertToEntity(bookingDto);
			bookingRepo.save(booking);
			return AllConstants.RIDER_SUCCESS_MESSAGE1;

		} catch (IllegalArgumentException e) {
			throw new BusinessException(HttpStatus.BAD_REQUEST);
		}

	}

	@Override
	public String deleteBookingById(Integer id) {
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
		List<BookingDto> booking = bookings.stream().map(this::convertToDto).collect(Collectors.toList());

		return booking;
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
		BookingDto book = convertToDto(booking);

		return book;
	}

	public BookingDto convertToDto(Booking booking) {
		BookingDto bookingDto = modelmap.map(booking, BookingDto.class);
		return bookingDto;
	}

	public Booking convertToEntity(BookingDto bookingDto) {
		Booking booking = modelmap.map(bookingDto, Booking.class);
		return booking;
	}

}
