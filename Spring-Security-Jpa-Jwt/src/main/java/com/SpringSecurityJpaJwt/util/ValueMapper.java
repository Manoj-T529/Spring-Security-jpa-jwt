package com.SpringSecurityJpaJwt.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.SpringSecurityJpaJwt.dto.UserRequestDto;
import com.SpringSecurityJpaJwt.dto.UserResponseDto;
import com.SpringSecurityJpaJwt.model.User;

@Component
public class ValueMapper {

	@Autowired
	private BCryptPasswordEncoder encoder;

	public User convertToEntity(UserRequestDto userRequestDto) {
		String pwd = userRequestDto.getPassword();
		String encrypted = encoder.encode(pwd);

		User user = new User();

		user.setName(userRequestDto.getName());
		user.setUsername(userRequestDto.getUsername());
		user.setPassword(encrypted);
		user.setAge(userRequestDto.getAge());
		user.setPhno(userRequestDto.getPhno());
		user.setGender(userRequestDto.getGender());
		user.setDob(userRequestDto.getDob());
		user.setAddress(userRequestDto.getAddress());
		user.setDob(userRequestDto.getDob());
		user.setRoles(userRequestDto.getRoles());

		return user;
	}

	public UserResponseDto convertToDTO(User user)
	{
		UserResponseDto userResponseDto = new UserResponseDto();
		
		userResponseDto.setUser_id(user.getUser_id());
		userResponseDto.setName(user.getName());
		userResponseDto.setUsername(user.getUsername());
		userResponseDto.setPassword(user.getPassword());
		userResponseDto.setAge(user.getAge());
		userResponseDto.setPhno(user.getPhno());
		userResponseDto.setGender(user.getGender());
		userResponseDto.setDob(user.getDob());
		userResponseDto.setAddress(user.getAddress());
		userResponseDto.setDob(user.getDob());
		userResponseDto.setRoles(user.getRoles());
		
		return userResponseDto;
		
	}
	
	public User convertToUpdateEntity(UserRequestDto userRequestDto, User user) {
		String pwd = userRequestDto.getPassword();
		String encrypted = encoder.encode(pwd);

		
		user.setName(userRequestDto.getName());
		user.setUsername(userRequestDto.getUsername());
		user.setPassword(encrypted);
		user.setAge(userRequestDto.getAge());
		user.setPhno(userRequestDto.getPhno());
		user.setGender(userRequestDto.getGender());
		user.setDob(userRequestDto.getDob());
		user.setAddress(userRequestDto.getAddress());
		user.setDob(userRequestDto.getDob());
		user.setRoles(userRequestDto.getRoles());
		
		return user;
	}

	
}
