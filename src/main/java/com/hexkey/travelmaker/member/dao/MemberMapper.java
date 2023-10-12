package com.hexkey.travelmaker.member.dao;

import com.hexkey.travelmaker.member.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper // Mybatis를 통해 진행하기 위해 Bean 등록
public interface MemberMapper {

    MemberDTO findIdByPhone(String memberId);

    String selectMemberById(String memberId);

}
