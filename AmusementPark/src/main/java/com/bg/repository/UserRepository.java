package com.bg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bg.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

	// @Query("SELECT u FROM Users u WHERE u.username = ?1 and u.password = ?2")
	// public Users validateUserCredentials(String username, String password);

	// public boolean existsByusername(String username);

//	Users findByUsername(String username);

	public boolean existsByPassword(String password);

	// public Optional<Users> findByEmail(String email);

	boolean existsByEmail(String email);

	Optional<Users> findByEmail(String email);

}
