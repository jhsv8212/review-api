package com.review.api.entity;

import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("게임id")
    private Long id;

    @Comment("게임 제목")
    @Column(nullable = false, length = 50)
    private String title;

    @Comment("제작사")
    @Column(nullable = false, length = 50)
    private String developer;

    @Comment("출시년도")
    @Column(nullable = false, length = 10)
    private int releaseYear;
}
