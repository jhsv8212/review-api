package com.review.api.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.review.api.config.JwtTokenProvider;
import com.review.api.entity.Member;
import com.review.api.exception.CommonException;
import com.review.api.repository.MemberRepository;
import com.review.api.response.CommonResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository repository;
    private final PasswordEncoder  passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public CommonResponse login(Member member) {
        Member getMember = repository.findByEmail(member.getEmail())
                .orElseThrow(() -> new CommonException("가입되지 않은 ID입니다."));
        if (!passwordEncoder.matches(member.getPassword(), getMember.getPassword())) {
            throw new CommonException("잘못된 비밀번호입니다.");
        }
        CommonResponse response = new CommonResponse();
        response.setResult("SUCCESS");
        response.setMessage("로그인 완료 되었습니다");
        response.setData(jwtTokenProvider.createToken(String.valueOf(getMember.getUsername()), getMember.getRoles()));

        return response;
    }

    public CommonResponse signUp(Member member) {

        CommonResponse response = new CommonResponse();

        try {
            member.setPassword(passwordEncoder.encode(member.getPassword()));
            member.setRoles(member.getEmail().equalsIgnoreCase("ADMIN") ?
                            Collections.singletonList("ROLE_ADMIN") : Collections.singletonList("ROLE_USER"));
            repository.save(member);

            response.setResult("SUCCESS");
            response.setMessage("가입 성공!");

        } catch (Exception e) {
            response.setResult("FAIL");
            response.setMessage("가입 실패!");
            e.printStackTrace();
        }
        return response;
    }

    public Optional<Member> findUserById(Long id) {
        return repository.findById(id);
    }


    public void modifyMember(Long id, Member member) {
    }

    public void deleteMemberById(Long id) {
        repository.deleteById(id);
    }

}
