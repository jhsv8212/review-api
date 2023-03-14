package com.review.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.review.api.entity.Users;
import com.review.api.repository.UserRepository;
import com.review.api.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserController {

	private final UserRepository repository;
	private final UserService service;

	@PostMapping("/login")
	private ResponseEntity<Void> loginUser(
			@RequestBody Users users
			) {

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/user")
	private ResponseEntity<Users> signUpUser(
			@RequestBody Users users
			) {
		service.signUpUser(users);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/user/{id}")
	private ResponseEntity<Void> modifyUser(
			@PathVariable Integer id,
			@RequestBody Users users
	) {
		service.modifyUser(id, users);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/user/{id}")
	private ResponseEntity<Void> deleteUserById(
			@PathVariable int id
	) {
		service.deleteUserById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
