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
//Value that indicates that only properties with non-nullvalues are to be included

public class UserDetailsDto {
	private Integer userId;
	private String firstName;
	private String lastName;
	private Integer age;
	private String userName;
	private String password;

}
