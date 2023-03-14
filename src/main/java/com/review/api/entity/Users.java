package com.review.api.entity;

import lombok.*;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class Users {
    @Id
    @Comment("사용자 id")
    @Column(length = 50)
    private String id;

    @Comment("사용자 닉네임")
    @Column(length = 10)
    private String nickName;

    @Comment("패스워드")
    @Column(nullable = false, length = 255)
//    @ColumnTransformer(
//            read = "password",
//            write = "SHA2(CONCAT(?,'{salt}'), 256)"
//    )
    private String password;

    @Comment("성별")
    @Column(length = 1)
    private String gender;

    @Comment("생년월일")
    private String birthYear;

}
