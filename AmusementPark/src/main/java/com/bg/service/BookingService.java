package com.bg.service;

import java.util.List;

import com.bg.entity.Booking;

public interface BookingService {

	String addRide(Booking ride);

	String updateRide(Booking ride);

	List<Booking> getAllBookings();

	String deleteRideById(int id);

	Booking getBookingById(Integer id);

}
