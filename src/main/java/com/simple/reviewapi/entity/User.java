package com.simple.reviewapi.entity;

import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("사용자 id")
    @Column(length = 10)
    private int userId;

    @Comment("사용자 닉네임")
    @Column(length = 10)
    private String nickName;

    @Comment("사용자 프로필 썸네일 URL")
    @Column(length = 50)
    private String imageUrl;

}