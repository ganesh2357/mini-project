package com.bg.service;

import java.util.List;

import com.bg.dto.BookingDto;
import com.bg.entity.Booking;

public interface BookingService {

	public String addRide(Booking ride);

	public String updateRide(Booking ride);

	public List<BookingDto> getAllBookings();

	public String deleteRideById(Integer id);

	public BookingDto getBookingById(Integer id);
	
	

}
