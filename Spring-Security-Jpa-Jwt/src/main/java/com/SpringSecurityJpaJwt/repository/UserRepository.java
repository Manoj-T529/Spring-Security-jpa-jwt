package com.SpringSecurityJpaJwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SpringSecurityJpaJwt.dto.UserResponseDto;
import com.SpringSecurityJpaJwt.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	String findByPhno(String phno);


}
