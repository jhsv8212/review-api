package com.review.api.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.review.api.entity.Review;
import com.review.api.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GameService {

    private final ReviewRepository reviewRepository;

    public Page<Review> findGameAll(Integer order, Integer page) {
        Sort sort = Sort.by(Sort.Direction.DESC, "reviewLike");
        if (order != null) {
            switch (order) {
                // 좋아요 순
                case 1 :
                    sort = Sort.by(Sort.Direction.DESC, "reviewLike");
                    break;
                // 최신 순
                case 2 :
                    sort = Sort.by(Sort.Direction.DESC, "updatedTime");
                    break;
                // 높은 평점 순
                case 3 :
                    sort = Sort.by(Sort.Direction.DESC, "reviewScore");
                    break;
                // 낮은 평점 순
                case 4 :
                    sort = Sort.by(Sort.Direction.ASC, "reviewScore");
                    break;
            }
        }
        return reviewRepository.findAll(PageRequest.of(page, 10, sort));
    }

    public Optional<Review> findGameById(Integer id) {
        return reviewRepository.findById(id);
    }

    public void saveGameById(Review review) {
        reviewRepository.save(review);
    }

    public void modifyGame(Integer id, Review review) {
        Optional<Review> select = reviewRepository.findById(id);
//
//        select.ifPresent(selectReview ->{
//            Review update = Review.builder()
//                    .reviewId(selectReview.getReviewId())
//                    .lessonId(selectReview.getLessonId())
//                    .userId(selectReview.getUserId())
//                    .reviewScore(review.getReviewScore())
//                    .reviewLike(review.getReviewLike())
//                    .reviewContent(review.getReviewContent())
//                    .createdTime(selectReview.getCreatedTime())
//                    .updatedTime(LocalDateTime.now())
//                    .build();
//
//            System.out.println("update: "+update);
//            reviewRepository.save(update);
//        });


        // toBuilder 사용하여 업데이트 항목만 적용
        select.ifPresent(selectReview ->{
            Review update = selectReview.toBuilder()
                    .reviewScore(review.getReviewScore())
                    .reviewLike(review.getReviewLike())
                    .reviewContent(review.getReviewContent())
                    .updatedTime(LocalDateTime.now())
                    .build();
            reviewRepository.save(update);
        });
    }

    public void deleteGameById(Integer id) {
        reviewRepository.deleteById(id);
    }

}
