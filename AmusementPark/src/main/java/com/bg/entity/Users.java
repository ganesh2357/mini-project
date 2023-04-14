package com.bg.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;

	@Column(name = "FIRST_NAME", nullable = false)
	@NotEmpty(message = "FIRST_NAME IS REQUIRED...")
	private String firstname;

	@Column(name = "LAST_NAME", nullable = false)
	@NotEmpty(message = "LAST_NAME IS REQUIRED...")
	private String lastname;

	@Column(name = "AGE", nullable = false)
	@NotNull(message = "AGE SHOULD NOT BE NULL...")
	private Integer age;

	@Column(name = "USER_NAME", nullable = false)
	@NotNull(message = "USER_NAME IS REQUIRED...")
	private String username;

	@Column(name = "EMAIL", nullable = false)
	@NotNull(message = "MAIL ID IS REQUIRED...")
	private String email;

	@Column(name = "PASSWORD", nullable = false)
	@NotEmpty(message = "PASSWORD IS REQUIRED...")
	private String password;

//	@ManyToOne
//	@JoinColumn(name="FK_ROLE_ID")
//	private Role role;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "BOOKING_ID")
	private List<Booking> booking;
}
