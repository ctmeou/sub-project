package com.hexkey.travelmaker.member.service;

import com.hexkey.travelmaker.member.dao.MemberMapper;
import com.hexkey.travelmaker.member.dto.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthenticationService implements UserDetailsService {

    // 의존성 주입 받아 진행
    private final MemberMapper memberMapper;

    public AuthenticationService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {

        // memberMapper에 조회 요청
        MemberDTO member = memberMapper.findIdByPhone(memberId);

        // 조회 정보 없을 시
        if (member == null) throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");

        return member;

    }

}
