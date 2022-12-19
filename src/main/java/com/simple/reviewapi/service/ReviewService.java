package com.simple.reviewapi.service;

import com.simple.reviewapi.entity.Review;
import com.simple.reviewapi.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Page<Review> findReviewAll(Integer order, Integer page) {
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

    public Optional<Review> findReviewById(Integer id) {
        return reviewRepository.findById(id);
    }

    public void saveReviewById(Review review) {
        reviewRepository.save(review);
    }

    public void modifyReview(Integer id, Review review) {
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

    public void deleteReviewById(Integer id) {
        reviewRepository.deleteById(id);
    }

}
