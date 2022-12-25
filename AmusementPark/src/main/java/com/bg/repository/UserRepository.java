package com.bg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bg.entity.UserDetails;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Integer> {

	@Query("SELECT u FROM UserDetails u WHERE u.userName = ?1 and u.password = ?2")
	public UserDetails validateUserCredentials(String userName, String password);

	public boolean existsByuserName(String userName);
	
	List<UserDetails> findByuserName(String userName);

}
