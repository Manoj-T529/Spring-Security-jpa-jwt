package com.SpringSecurityJpaJwt.dto;

import java.sql.Date;
import java.util.Set;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

	private long user_id;
	
	private String name;
	
	private String username;
	
	private String password;
	
	private int age;
	
	private String phno;
	
	private String address;
	
	private String gender;
	
	private Date dob;
	
	private Set<String> roles;
}
