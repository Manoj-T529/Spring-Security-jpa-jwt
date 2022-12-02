package com.SpringSecurityJpaJwt.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringSecurityJpaJwt.dto.UserResponseDto;
import com.SpringSecurityJpaJwt.dto.UserRequestDto;
import com.SpringSecurityJpaJwt.execptions.AlreadyExistsException;
import com.SpringSecurityJpaJwt.execptions.UserNotFoundException;
import com.SpringSecurityJpaJwt.model.User;
import com.SpringSecurityJpaJwt.repository.UserRepository;
import com.SpringSecurityJpaJwt.util.ValueMapper;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private ValueMapper valueMapper;

	public User createProfile(@Valid UserRequestDto userRequestDto) {

		User name = repository.findByUsername(userRequestDto.getUsername());

		if (name != null) {
			throw new AlreadyExistsException("User with " + name.getUsername() + " is already exists");
		}

		String phno = repository.findByPhno(userRequestDto.getPhno());

		if (phno != null) {
			throw new AlreadyExistsException(phno + "is already registered");
		}

		User user = valueMapper.convertToEntity(userRequestDto);

		return repository.save(user);
	}

	public User findByUsername(String username) {

		User user = repository.findByUsername(username);

		if (user == null) {
			throw new UserNotFoundException("User does Not exists");
		}

		return user;
	}

	public UserResponseDto getDataById(Long user_id) {

		UserResponseDto userResponseDto;

		Optional<User> user = repository.findById(user_id);

		if (user.isEmpty()) {
			throw new UserNotFoundException("No Users Found for given ID");
		}

		userResponseDto = valueMapper.convertToDTO(user.get());

		return userResponseDto;
	}

	public UserResponseDto updateData(@Valid UserRequestDto userRequestDto, Long user_id) {

		Optional<User> user = repository.findById(user_id);

		if (user.isEmpty()) {
			throw new UserNotFoundException("No Users Found for given ID");
		}

		User userResponse = valueMapper.convertToUpdateEntity(userRequestDto, user.get());

		User updatedUserResponse = repository.save(userResponse);

		UserResponseDto userResponseDto = valueMapper.convertToDTO(updatedUserResponse);

		return userResponseDto;
	}

	public void deleteById(Long user_id) {

		Optional<User> user = repository.findById(user_id);

		if (user.isEmpty()) {
			throw new UserNotFoundException("No Users Found for given ID");
		}

		repository.deleteById(user_id);
	}

}
