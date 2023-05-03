package com.bg.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {
	private Integer id;
//	private String landRide;
//	private String waterRide;
	// private Date date;
	private String rideName;
	private LocalDate date;
	private Integer noOfRides;
	private Integer costOfRide;
	private Integer totalFare;

}
