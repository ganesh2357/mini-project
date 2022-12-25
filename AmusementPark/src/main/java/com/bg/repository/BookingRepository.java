package com.bg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bg.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

	
}
