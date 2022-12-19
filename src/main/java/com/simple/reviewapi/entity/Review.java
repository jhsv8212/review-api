package com.simple.reviewapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("리뷰 id")
    @Column(length = 10)
    private int reviewId;

    @Comment("리뷰 작성자 정보")
    @Column(length = 10)
    private int userId;

    @Comment("강의 id")
    @Column(length = 10)
    private int lessonId;

    @Comment("리뷰 평점")
    @Column(length = 1)
    private Integer reviewScore;

    @Comment("리뷰 좋아요 개수")
    @Column(length = 10)
    private Integer reviewLike;

    @Comment("리뷰 본문")
    @Column(length = 1000)
    private String reviewContent;

    @Comment("리뷰 생성 일시")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;

    @Comment("리뷰 수정 일시")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedTime;

}