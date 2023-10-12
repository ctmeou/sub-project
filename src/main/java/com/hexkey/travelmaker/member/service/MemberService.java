package com.hexkey.travelmaker.member.service;

import com.hexkey.travelmaker.member.dao.MemberMapper;
import com.hexkey.travelmaker.member.dto.MemberDTO;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    // DB 호출해서 확인하기 때문에 memberMapper 호출
    private final MemberMapper memberMapper;

    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public boolean selectMemberById(String memberId) {

        String result = memberMapper.selectMemberById(memberId);

        return result != null;
        // 중복된 아이디 true 반환

    }

    public void registMemebr(MemberDTO member) {
    }

//    // 회원 가입
//    @Transactional
//    public void registMemebr(MemberDTO member) throws MemberRegistException {
//
//        member.setLoginType("일반가입");
//
//        int result1 = memberMapper.insertMember(member);
//        int result2 = memberMapper.insertMemberRole();
//
//        if (!(result1 > 0 && result2 > 0)) throw new MemberRegistException("회원 가입에 실패했습니다.");
//
//    }
//
//    public boolean selectMemberById(String memberId) {
//
//        String result = memberMapper.selectMemberById(memberId);
//
//        return  result != null;
//
//    }
//
//    // 로그인 화면

}
