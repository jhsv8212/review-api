package com.simple.reviewapi.service;

import com.simple.reviewapi.entity.Lesson;
import com.simple.reviewapi.entity.Review;
import com.simple.reviewapi.entity.User;
import com.simple.reviewapi.repository.LessonRepository;
import com.simple.reviewapi.repository.ReviewRepository;
import com.simple.reviewapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class DataInitailizing {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final LessonRepository lessonRepository;


    /**
     * 테스트를 위한 데이터 임시 세팅
     */
    @PostConstruct
    public void initializing() {
        for (int i = 0; i < 30; i++) {
            Review review = Review.builder()
                    .reviewId(i+1)
                    .userId((int)(Math.random()*5 + 1))
                    .lessonId((int)(Math.random()*3 + 1))
                    .reviewScore((int)(Math.random()*5 + 1))
                    .reviewLike((int)(Math.random()*999))
                    .reviewContent("리뷰 내용~")
                    .createdTime(LocalDateTime.now())
                    .updatedTime(LocalDateTime.now())
                    .build();
            reviewRepository.save(review);
        }

        for (int i = 1; i <= 3; i++) {
            User user = User.builder()
                    .userId(i)
                    .nickName("유저" + i)
                    .imageUrl("https://www.test.com/image.png")
                    .build();
            userRepository.save(user);
        }

        for (int i = 0; i < 5; i++) {
            Lesson lesson = Lesson.builder()
                    .lessonId(i+1)
                    .lessonName("스프링 부트와 JPA로 초간단 API 개발하기 " + i +"편")
                    .teacherName("신한큐브온")
                    .level("초급")
                    .duration("무제한")
                    .build();
            lessonRepository.save(lesson);
        }

    }
}
