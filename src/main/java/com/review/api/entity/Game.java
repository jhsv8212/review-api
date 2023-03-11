package com.review.api.entity;

import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("게임id")
    @Column(length = 10)
    private int gameId;

    @Comment("게임 제목")
    @Column(nullable = false, length = 50)
    private String gameName;

    @Comment("제작사")
    @Column(nullable = false, length = 50)
    private String publisher;

    @Comment("출시년도")
    @Column(nullable = false, length = 10)
    private int publishYear;

}
