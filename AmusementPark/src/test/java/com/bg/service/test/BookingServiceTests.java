package com.bg.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.bg.dto.BookingDto;
import com.bg.entity.Booking;
import com.bg.repository.BookingRepository;
import com.bg.service.BookingService;
import com.bg.util.AllConstants;

@RunWith(SpringRunner.class)
@SpringBootTest
class BookingServiceTests {
	@Autowired
	private BookingService bookingService;
	@MockBean
	private BookingRepository bookingRepository;

	@Autowired
	ModelMapper modelMapper;

	@Test
	void addBookingTest() {
		Booking booking = new Booking(1, "Landride", "Waterride", 1, 300, 300);
		when(bookingRepository.save(booking)).thenReturn(booking);
		assertEquals(AllConstants.RIDER_SUCCESS_MESSAGE, bookingService.addRide(booking));
	}
	
	@Test
	void updateRideTest() {
		Booking booking = new Booking(1, "Landride", "Waterride", 1, 300, 300);
		when(bookingRepository.save(booking)).thenReturn(booking);
		assertEquals(AllConstants.RIDER_SUCCESS_MESSAGE1, bookingService.updateRide(booking));
	}

	@Test
	void getBookingByIdTest() {
		Integer id = 1;
		Optional<Booking> bookingOpt = Optional.of(new Booking(1, "Landride", "Waterride", 1, 300, 300));
		when(bookingRepository.findById(id)).thenReturn(bookingOpt);
		Booking booking = bookingOpt.get();
		assertEquals(modelMapper.map(booking, BookingDto.class), bookingService.getBookingById(id));
	}

	@Test
	void getAllTicketsTest() {
		when(bookingRepository.findAll()).thenReturn(Stream.
				of(new Booking(1, "Landride", "Waterride", 4, 250, 1000),
				new Booking(2, "Waterride", "LandRide", 2, 300, 600)).collect(Collectors.toList()));
		assertEquals(2, bookingService.getAllBookings().size());
	}
	
	@Test
	void deleteRideTest() {
		Integer id=1;
		Optional<Booking> bookingOpt = Optional.of(new Booking(1, "Landride", "Waterride", 1, 300, 300));
		when(bookingRepository.findById(id)).thenReturn(bookingOpt);
		if(bookingRepository.existsById(id)) {
			bookingService.deleteRideById(id);
			verify(bookingRepository,times(1)).deleteById(id);
	}
	
	
	
	
		

	}

}
