package com.review.api.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.review.api.entity.Review;
import com.review.api.entity.Users;
import com.review.api.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public void signUpUser(Users users) {
        Users encode = users.toBuilder()
                .password(passwordEncoder.encode(users.getPassword()))
                .build();
        repository.save(users);
    }

    public Optional<Users> findReviewById(Integer id) {
        return repository.findById(id);
    }


    public void modifyUser(Integer id, Users users) {
    }

    public void deleteUserById(Integer id) {
        repository.deleteById(id);
    }

}
