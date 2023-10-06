package com.hexkey.travelmaker.member.controller;

import com.hexkey.travelmaker.member.common.MemberRegistException;
import com.hexkey.travelmaker.member.dto.AddressDTO;
import com.hexkey.travelmaker.member.dto.MemberDTO;
import com.hexkey.travelmaker.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final MessageSourceAccessor messageSourceAccessor;

    public MemberController(MemberService memberService, PasswordEncoder passwordEncoder, MessageSourceAccessor messageSourceAccessor) {
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
        this.messageSourceAccessor = messageSourceAccessor;
    }

    @GetMapping("/login")
    public void loginPage() {}

    @GetMapping("/regist")
    public void reigstPage() {}


    // 회원가입
    @PostMapping("/regist")
    public String registMember(AddressDTO address, MemberDTO member, String zipCode, String address1, String address2,
                               RedirectAttributes rttr) throws MemberRegistException {

        address.setPostalCode(Integer.parseInt(zipCode));
        address.setDefaultAdr(address1);
        address.setOptionAdr(address2);

        member.setMemberPwd(passwordEncoder.encode(member.getPassword()));

        log.info("Request regist member : {}", member);

        memberService.registMemebr(member);

        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("member.regist"));

        return "redirect:/";

    }


}
