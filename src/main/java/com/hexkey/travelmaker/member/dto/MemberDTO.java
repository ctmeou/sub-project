package com.hexkey.travelmaker.member.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter @ToString
public class MemberDTO {

    private int MemberCode;
    private String MemberName;
    private int phone;
    private String email;
    private String memberId;
    private String memberPwd;
    private int tel;

    // 한 멤버는 여러 권한을 가질 수 있다.
    private List<MemberRoleDTO> memberRoleList;

    public String getPassword() {
        return memberPwd;
    }

}
