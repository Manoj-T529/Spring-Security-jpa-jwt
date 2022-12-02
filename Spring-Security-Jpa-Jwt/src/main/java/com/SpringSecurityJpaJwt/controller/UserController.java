package com.SpringSecurityJpaJwt.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringSecurityJpaJwt.dto.APIResponse;
import com.SpringSecurityJpaJwt.dto.LoginDto;
import com.SpringSecurityJpaJwt.dto.UserRequestDto;
import com.SpringSecurityJpaJwt.dto.UserResponseDto;
import com.SpringSecurityJpaJwt.jwt.JwtUtil;
import com.SpringSecurityJpaJwt.model.User;
import com.SpringSecurityJpaJwt.service.UserService;

/**
 * @author manoj
 *
 */

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService service;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	// url : http://localhost:10002/api/user/register

	@PostMapping("/register")
	public ResponseEntity<APIResponse<User>> createProfile(@Valid @RequestBody UserRequestDto userRequestDto) {

		User userService = service.createProfile(userRequestDto);
		// Builder Design pattern (to avoid complex object creation headache)
		APIResponse<User> user = APIResponse.<User>builder().status("User Created Successfully").build();

		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	// url : http://localhost:10002/api/user/login

	@PostMapping("/login")
	public ResponseEntity<APIResponse<User>> login(@Valid @RequestBody LoginDto loginDto) {
		User userService = service.findByUsername(loginDto.getUsername());

		// to authenticate the username and password provided while login
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

		String token = jwtUtil.generateToken(loginDto.getUsername());
		// Builder Design pattern (to avoid complex object creation headache)
		APIResponse<User> apiResponse = APIResponse.<User>builder().status("User Successfully Logged In").message(token)
				.build();

		return new ResponseEntity<>(apiResponse, HttpStatus.ACCEPTED);
	}

	// url : http://localhost:10002/api/user/getdata/2

	@GetMapping("/getdata/{user_id}")
	public ResponseEntity<APIResponse<UserResponseDto>> getData(@PathVariable(value = "user_id") Long user_id) {
		UserResponseDto userResponseDto = service.getDataById(user_id);
		// Builder Design pattern (to avoid complex object creation headache)
		APIResponse<UserResponseDto> apiResponse = APIResponse.<UserResponseDto>builder().status("Success")
				.results(userResponseDto).build();

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	// url : http://localhost:10002/api/user/updatedata/1

	@PutMapping("/updatedata/{user_id}")
	public ResponseEntity<APIResponse<UserResponseDto>> updateData(@Valid @RequestBody UserRequestDto userRequestDto,
			@PathVariable(value = "user_id") Long user_id) {

		UserResponseDto userResponseDto = service.updateData(userRequestDto, user_id);
		// Builder Design pattern (to avoid complex object creation headache)
		APIResponse<UserResponseDto> apiResponse = APIResponse.<UserResponseDto>builder()
				.status("User Data Updated Successfully").results(userResponseDto).build();

		return new ResponseEntity<>(apiResponse, HttpStatus.ACCEPTED);
	}

	// url : http://localhost:10002/api/user/deletedata/1

	@DeleteMapping("/deletedata/{user_id}")
	public ResponseEntity<String> deleteData(@PathVariable(value = "user_id") Long user_id) {
		service.deleteById(user_id);
		return new ResponseEntity<String>("User Deleted Successfully", HttpStatus.OK);
	}
}
