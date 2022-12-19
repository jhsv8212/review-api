package com.simple.reviewapi.controller;

import com.simple.reviewapi.entity.Lesson;
import com.simple.reviewapi.entity.Review;
import com.simple.reviewapi.entity.User;
import com.simple.reviewapi.repository.LessonRepository;
import com.simple.reviewapi.repository.ReviewRepository;
import com.simple.reviewapi.repository.UserRepository;
import com.simple.reviewapi.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

import javax.annotation.PostConstruct;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final LessonRepository lessonRepository;

    private final ReviewService service;

    @GetMapping("/reviews")
    private ResponseEntity<Page<Review>> findReviewAll(
            @RequestParam @Nullable Integer order,
            @RequestParam Integer page) {

        return ResponseEntity.ok(service.findReviewAll(order, page));
    }

    @GetMapping("/review/{id}")
    private ResponseEntity<Optional<Review>> findReviewById(
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(service.findReviewById(id));
    }

    @PostMapping("/review")
    private ResponseEntity<Void> saveReviewById(
            @RequestBody Review review
    ) {
        service.saveReviewById(review);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/review")
    private ResponseEntity<Void> modifyReview(
            @RequestBody Review review
    ) {
        service.modifyReview(review);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/review/{id}")
    private ResponseEntity<Void> deleteReviewById(
            @PathVariable int id
    ) {
        service.deleteReviewById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
