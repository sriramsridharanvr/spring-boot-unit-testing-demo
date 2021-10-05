package com.yethi.people.data.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "persons")
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "person_id", nullable = false, unique = true)
	private String personId;
	
	@Column(name = "first_name", nullable = false, length = 16)
	private String firstName;
	
	@Column(name = "last_name", length = 16)
	private String lastName;
	
	@Column(nullable = false, unique = true, length = 60)
	private String email;
	
	@Column(name = "date_of_birth", nullable = false)
	private LocalDate dateOfBirth;
	
	@Column(length = 24)
	private String city;
	
	@Column(length = 3)
	private String countryCode;
	
}
