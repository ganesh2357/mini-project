package com.bg.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class BookingDto {
	private Integer id;
	private String landRide;
	private String waterRide;
	private Integer noOfRides;
	private Integer costOfRide;
	private Integer totalFare;

}
