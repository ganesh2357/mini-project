package com.bg.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bg.entity.Booking;
import com.bg.repository.BookingRepository;
import com.bg.service.BookingService;

@SpringBootTest
public class BookingServiceTests {
	@Autowired
	private BookingService bookingService;
	@MockBean
	private BookingRepository bookingRepository;

	@Test
	void getAllTicketsTest() {
		when(bookingRepository.findAll()).thenReturn(Stream.of(
				new Booking(1, "Landride", "Waterride", 4, 250, 1000),
				new Booking(2, "Waterride", "LandRide", 2, 300, 600))
				.collect(Collectors.toList()));
		assertEquals(2, bookingService.getAllBookings().size());
	}

	@Test
	void addBookingTest() {
		Booking booking = new Booking(1, "Landride", "Waterride", 1, 300, 300);
		when(bookingRepository.save(booking)).thenReturn(booking);
		assertEquals(booking, bookingService.addRide(booking));
	}
	
	@Test
	void getBookingByIdTest() {
		Integer id = 1;
		Optional<Booking> bookingOpt = Optional.of(new Booking());
		when(bookingRepository.findById(id))
		.thenReturn(bookingOpt);
		Booking booking = bookingOpt.get();
		assertEquals(booking, bookingService.getBookingById(id));
	}
}
