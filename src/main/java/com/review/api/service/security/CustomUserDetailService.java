package com.review.api.service.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.review.api.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository repository;

    public UserDetails loadUserByUsername(String userPk) {
        return repository.findByEmail(userPk).orElseThrow(() -> new UsernameNotFoundException("유저 정보가 존재하지 않습니다."));
    }
}
