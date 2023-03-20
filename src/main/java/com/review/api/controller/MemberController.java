package com.review.api.controller;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.review.api.config.JwtTokenProvider;
import com.review.api.entity.Member;
import com.review.api.repository.MemberRepository;
import com.review.api.response.CommonResponse;
import com.review.api.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MemberController {

	private final MemberRepository repository;
	private final MemberService service;
	private final PasswordEncoder passwordEncoder;

	@PostMapping("/login")
	private ResponseEntity<CommonResponse> loginUser(
			@RequestBody Member member
			) {
		return new ResponseEntity<>(service.login(member), HttpStatus.OK);
	}

	@PostMapping("/signup")
	private ResponseEntity<CommonResponse> signUpUser(
			@RequestBody Member member
			) {
		return new ResponseEntity<>(service.signUp(member), HttpStatus.OK);
	}

	@PutMapping("/user/{id}")
	private ResponseEntity<Void> modifyUser(
			@PathVariable Long id,
			@RequestBody Member member
	) {
		service.modifyMember(id, member);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/user/{id}")
	private ResponseEntity<Void> deleteUserById(
			@PathVariable Long id
	) {
		service.deleteMemberById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
