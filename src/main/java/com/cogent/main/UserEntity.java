package com.cogent.main;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique=true, nullable = false)
	private String email;
	private String userName;
	@Column(nullable = false)
	private String password;
	@Embedded
	private Name name;
	@Embedded
	private Address address;
	private String phone;
	private boolean admin;
	private String jwtToken;
}
