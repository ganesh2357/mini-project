package com.bg.service;

import java.util.List;

import com.bg.dto.BookingDto;

public interface BookingService {

	public String addBooking(BookingDto bookingDto);

	public String updateBooking(BookingDto bookingDto);

	public List<BookingDto> getAllBookings();

	public String deleteBookingById(Integer id);

	public BookingDto getBookingById(Integer id);

}
