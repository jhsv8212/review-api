package com.simple.reviewapi.controller;

import com.simple.reviewapi.entity.Review;
import com.simple.reviewapi.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @PutMapping("/review/{id}")
    private ResponseEntity<Void> modifyReview(
            @PathVariable Integer id,
            @RequestBody Review review
    ) {
        service.modifyReview(id, review);
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
