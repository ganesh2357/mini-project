package com.bg.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class BookingDto {
	private Integer id;
	private String landRide;
	private String waterRide;
	private Integer noOfRides;
	private Integer costOfRide;
	private Integer totalFare;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLandRide() {
		return landRide;
	}

	public void setLandRide(String landRide) {
		this.landRide = landRide;
	}

	public String getWaterRide() {
		return waterRide;
	}

	public void setWaterRide(String waterRide) {
		this.waterRide = waterRide;
	}

	public Integer getNoOfRides() {
		return noOfRides;
	}

	public void setNoOfRides(Integer noOfRides) {
		this.noOfRides = noOfRides;
	}

	public Integer getCostOfRide() {
		return costOfRide;
	}

	public void setCostOfRide(Integer costOfRide) {
		this.costOfRide = costOfRide;
	}

	public Integer getTotalFare() {
		return totalFare;
	}

	public void setTotalFare(Integer totalFare) {
		this.totalFare = totalFare;
	}

	public BookingDto() {
		super();
	}

	public BookingDto(Integer id, String landRide, String waterRide, Integer noOfRides, Integer costOfRide,
			Integer totalFare) {
		super();
		this.id = id;
		this.landRide = landRide;
		this.waterRide = waterRide;
		this.noOfRides = noOfRides;
		this.costOfRide = costOfRide;
		this.totalFare = totalFare;
	}

	@Override
	public String toString() {
		return "BookingDto [id=" + id + ", landRide=" + landRide + ", waterRide=" + waterRide + ", noOfRides="
				+ noOfRides + ", costOfRide=" + costOfRide + ", totalFare=" + totalFare + "]";
	}

}
