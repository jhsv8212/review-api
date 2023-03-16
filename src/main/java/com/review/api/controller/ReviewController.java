package com.review.api.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.review.api.entity.Review;
import com.review.api.service.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService service;

    @GetMapping("/reviews")
    private ResponseEntity<Page<Review>> findReviewAll(
            @RequestParam @Nullable Integer order,
            @RequestParam Integer page) {

        return ResponseEntity.ok(service.findReviewAll(order, page));
    }

    @GetMapping("/review/{id}")
    private ResponseEntity<Optional<Review>> findReviewById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.findReviewById(id));
    }

    @PostMapping("/review")
    private ResponseEntity<Void> saveReviewById(
            @RequestBody Review review
    ) {
        service.saveReview(review);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/review/{id}")
    private ResponseEntity<Void> modifyReview(
            @PathVariable Long id,
            @RequestBody Review review
    ) {
        service.modifyReview(id, review);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/review/{id}")
    private ResponseEntity<Void> deleteReviewById(
            @PathVariable Long id
    ) {
        service.deleteReviewById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
