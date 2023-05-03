package com.bg.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BOOKING_DETAILS")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

//	@Column(name = "LAND_RIDE", nullable = false)
//	@NotEmpty(message = "TYPE OF RIDE IS REQUIRED...")
//	private String landRide;
//
//	@Column(name = "WATER_RIDE", nullable = false)
//	@NotEmpty(message = "TYPE OF RIDE IS REQUIRED...")
//	private String waterRide;

	@Column(name = "TYPE_OF_RIDE")
	private String rideName;

	// @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_OF_RIDE")
	// private Date date;
	private LocalDate date;

	@Column(name = "TOTAL_RIDES", nullable = false)
	@NotNull(message = "NO OF RIDES IS REQUOIRED...")
	private Integer noOfRides;

	@Column(name = "RIDE_COST", nullable = false)
	private Integer costOfRide;
	@Column(name = "TOTAL_FARE", nullable = false)
	private Integer totalFare;

}
