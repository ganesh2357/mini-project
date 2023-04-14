package com.bg.dto;

import java.util.Date;

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
	private String landRide;
	private String waterRide;
	private Date date;
	private Integer noOfRides;
	private Integer costOfRide;
	private Integer totalFare;

}
