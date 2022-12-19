package com.simple.reviewapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="class")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("강의id")
    @Column(nullable = false, length = 10)
    private int lessonId;

    @Comment("강의 제목")
    @Column(nullable = false, length = 50)
    private String lessonName;

    @Comment("강사 이름")
    @Column(nullable = false, length = 50)
    private String teacherName;

    @Comment("난도")
    @Column(nullable = false, length = 10)
    private String level;

    @Comment("수강기한")
    @Column(nullable = false, length = 10)
    private String duration;

//    @Builder
//    public User(String id, String name) {
//        this.id = id;
//        this.name = name;
//    }
}