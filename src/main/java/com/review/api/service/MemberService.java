package com.review.api.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.review.api.entity.Member;
import com.review.api.repository.MemberRepository;
import com.review.api.response.CommonResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository repository;
    private final PasswordEncoder passwordEncoder;

    public CommonResponse signUp(Member member) {

        CommonResponse result = new CommonResponse();

        try {
            member.setPassword(passwordEncoder.encode(member.getPassword()));
            member.setRoles(member.getEmail().equalsIgnoreCase("ADMIN") ?
                            Collections.singletonList("ROLE_ADMIN") : Collections.singletonList("ROLE_USER"));
            repository.save(member);

            result.setCode(1);
            result.setMessage("가입 성공!");

        } catch (Exception e) {
            result.setCode(-1);
            result.setMessage("가입 실패!");
            e.printStackTrace();
        }
        return result;
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
