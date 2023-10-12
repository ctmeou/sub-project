package com.hexkey.travelmaker.member.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class AddressDTO {

    private int addressCode;        // 주소 코드
    private String addressName;     // 주소 이름
    private int postalCode;         // 우편 번호
    private int memberCode;         // 회원 코드
    private String defaultAdr;      // 기본 주소
    private String optionAdr;       // 상세 주소
    private int defaultYn;          // 기본 배송지 여부

}
