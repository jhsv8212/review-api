package com.review.api.service;

import com.review.api.entity.Review;
import com.review.api.repository.GameRepository;
import com.review.api.repository.ReviewRepository;
import com.review.api.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final GameRepository gameRepository;

    public Page<Review> findReviewAll(Integer order, Integer page) {
        Sort sort = Sort.by(Sort.Direction.DESC, "likeCount");
        if (order != null) {
            switch (order) {
                // 좋아요 순
                case 1 :
                    sort = Sort.by(Sort.Direction.DESC, "likeCount");
                    break;
                // 최신 순
                case 2 :
                    sort = Sort.by(Sort.Direction.DESC, "updatedAt");
                    break;
                // 높은 평점 순
                case 3 :
                    sort = Sort.by(Sort.Direction.DESC, "rating");
                    break;
                // 낮은 평점 순
                case 4 :
                    sort = Sort.by(Sort.Direction.ASC, "rating");
                    break;
            }
        }
        return reviewRepository.findAll(PageRequest.of(page, 10, sort));
    }

    public Optional<Review> findReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    public void saveReview(Review review) {
        review.setCreatedAt(LocalDateTime.now());
        review.setUpdatedAt(LocalDateTime.now());
        reviewRepository.save(review);


    }

    public void modifyReview(Long id, Review review) {

        // toBuilder 사용하여 업데이트 항목만 적용
        // Optional<Review> select = reviewRepository.findById(id);
        // select.ifPresent(selectReview ->{
        //     Review update = selectReview.toBuilder()
        //             .rating(review.getRating())
        //             .likeCount(review.getLikeCount())
        //             .content(review.getContent())
        //             .updatedAt(LocalDateTime.now())
        //             .build();
        //     reviewRepository.save(update);
        // });
        Optional<Review> select = reviewRepository.findById(id);
        select.ifPresent((selectReview ->{
            selectReview.setGame(review.getGame());
            selectReview.setRating(review.getRating());
            selectReview.setLikeCount(review.getLikeCount());
            selectReview.setContent(review.getContent());
            selectReview.setUpdatedAt(LocalDateTime.now());
            reviewRepository.save(selectReview);
        }));

    }

    public void deleteReviewById(Long id) {
        reviewRepository.deleteById(id);
    }

    // public static void setRelationship(EntityManager em) {
    //     EntityTransaction tx = em.getTransaction();
    //
    //     Review review = em.find(Review.class, 1L);
    //     Game game = review.getGame();
    //
    //     log.info("game : {}", game);
    //
    //     tx.commit();
    //     em.close();
    // }

}
