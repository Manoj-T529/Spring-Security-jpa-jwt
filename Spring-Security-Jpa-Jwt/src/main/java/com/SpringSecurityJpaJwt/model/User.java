package com.SpringSecurityJpaJwt.model;

import java.sql.Date;
import java.util.*;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Userr")
@Data
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long user_id;

	private String name;

	private String username;

	private String password;

	private int age;

	private String phno;

	private String address;

	private String gender;

	private Date dob;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "rolee", joinColumns = @JoinColumn(name = "user_id"))
	private Set<String> roles;
}
