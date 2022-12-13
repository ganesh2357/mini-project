package com.bg.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
//Value that indicates that only properties with non-nullvalues are to be included

public class UserDetailsDto {
	private Integer userId;
	private String firstName;
	private String lastName;
	private Integer age;
	private String userName;
	private String password;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserDetailsDto(Integer userId, String firstName, String lastName, Integer age, String userName,
			String password) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.userName = userName;
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserDetailsDto [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", age="
				+ age + ", userName=" + userName + ", password=" + password + "]";
	}

}
