package com.hexkey.travelmaker.member.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Getter @Setter @ToString
public class MemberDTO implements UserDetails {

    private int memberCode;         // 회원 코드
    private String memberName;      // 회원 이름
    private int phone;              // 휴대폰 번호
    private String email;           // 이메일
    private String memberId;        // 회원 아이디
    private String memberPwd;       // 비밀번호
    private int smsYn;              // sms 수신 여부
    private int emailYn;            // email 수신 여부
    private int mileage;            // 적립금
    private int policyYn;           // 이용약관 동의 여부
    private int privateYn;          // 개인정보 동의 여부
    private int promotionYn;        // 쇼핑정보 동의 여부
    private int gradeCode;          // 회원 등급
    private String loginType;       // 로그인 경로
    private int tel;                // 일반전화
    private Date joinDay;           // 가입일
    private String memberStatus;    // 회원 상태
    private List<MemberRoleDTO> memberRoleList;
    // 한 멤버는 여러 권한을 가질 수 있다.

    // 권한이 뭐가 있는지 확인하고 로그인하는 객체에 권한 제공
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        for(MemberRoleDTO role : memberRoleList) {
            roles.add(new SimpleGrantedAuthority(role.getAuthority().getName()));
        }
        return roles;
    }

    @Override
    public String getPassword() {
        return memberPwd;
    }

    @Override
    public String getUsername() {
        return memberId;
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
