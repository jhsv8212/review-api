package com.review.api.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import lombok.*;

import org.hibernate.annotations.Comment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="member")
public class Member implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("사용자 id")
    // @Column(name = "user_id")
    private Long id;

    @Comment("사용자 email")
    @Column(nullable = false, unique = true, length = 30)
    private String email;

    @Comment("사용자 닉네임")
    @Column(nullable = false, length = 10)
    private String nickName;

    @Comment("패스워드")
    @Column(nullable = false)
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

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
