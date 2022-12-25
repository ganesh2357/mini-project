package com.bg.service;

import java.util.List;

import com.bg.entity.Booking;

public interface BookingService {

	public String addRide(Booking ride);

	String updateRide(Booking ride);

	List<Booking> getAllBookings();

	String deleteRideById(Integer id);

	Booking getBookingById(Integer id);
	
	

}
