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
import com.review.api.service.GameService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class GameController {
    private final GameService service;

    @GetMapping("/games")
    private ResponseEntity<Page<Review>> findReviewAll(
            @RequestParam @Nullable Integer order,
            @RequestParam Integer page) {

        return ResponseEntity.ok(service.findGameAll(order, page));
    }

    @GetMapping("/game/{id}")
    private ResponseEntity<Optional<Review>> findReviewById(
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(service.findGameById(id));
    }

    @PostMapping("/game")
    private ResponseEntity<Void> saveReviewById(
            @RequestBody Review review
    ) {
        service.saveGameById(review);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/game/{id}")
    private ResponseEntity<Void> modifyReview(
            @PathVariable Integer id,
            @RequestBody Review review
    ) {
        service.modifyGame(id, review);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/game/{id}")
    private ResponseEntity<Void> deleteReviewById(
            @PathVariable int id
    ) {
        service.deleteGameById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
