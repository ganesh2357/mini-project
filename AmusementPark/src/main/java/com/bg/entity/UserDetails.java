package com.bg.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_DETAILS")
public class UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;

	@Column(name = "FIRST_NAME", nullable = false)
	@NotEmpty(message = "FIRST_NAME IS REQUIRED...")
	private String firstName;

	@Column(name = "LAST_NAME", nullable = false)
	@NotEmpty(message = "LAST_NAME IS REQUIRED...")
	private String lastName;

	@Column(name = "AGE", nullable = false)
	@NotNull(message = "AGE SHOULD NOT BE NULL...")
	private Integer age;

	@Column(name = "USER_NAME", nullable = false)
	@NotNull(message = "USER_NAME IS REQUIRED...")
	private String userName;

	@Column(name = "PASSWORD", nullable = false)
	@NotEmpty(message = "PASSWORD IS REQUIRED...")
	private String password;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_BOOKING")
	private List<Booking> booking;
}
