package com.hexkey.travelmaker.member.controller;

import com.hexkey.travelmaker.member.common.exception.MemberRegistException;
import com.hexkey.travelmaker.member.dto.AddressDTO;
import com.hexkey.travelmaker.member.dto.MemberDTO;
import com.hexkey.travelmaker.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/user")
public class MemberController {

    private final MemberService memberService;
    private final MessageSourceAccessor messageSourceAccessor;

    public MemberController(MemberService memberService, MessageSourceAccessor messageSourceAccessor) {
        this.memberService = memberService;
        this.messageSourceAccessor = messageSourceAccessor;
    }

    // 로그인 페이지로 이동
    @GetMapping("/login")
    public void loginPage() {}

    // 로그인 실패 시
    @PostMapping("/loginfail")
    public String loginfail(RedirectAttributes rttr) {
        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("error.login"));
        return "redirect:/user/login";
    }

    // 회원 가입 페이지 이동
    @GetMapping("/regist")
    public void reigstPage() {}

    // 아이디 중복 체크
    @PostMapping("/idCheck")
    public ResponseEntity<String> checkDuplication(@RequestBody MemberDTO member) {

        log.info("Request Check ID : {}", member.getMemberId());

        String result = "사용 가능한 아이디입니다.";

        if (memberService.selectMemberById(member.getMemberId())) {
            result = "중복된 아이디가 존재합니다.";
        }

        return ResponseEntity.ok(result);

    }

    // 회원가입
    @PostMapping("/regist")
    public String registMember(AddressDTO address, MemberDTO member, String zipCode, String address1, String address2,
                               RedirectAttributes rttr) throws MemberRegistException {

        address.setPostalCode(Integer.parseInt(zipCode));
        address.setDefaultAdr(address1);
        address.setOptionAdr(address2);

        log.info("Request regist member : {}", member);

        memberService.registMemebr(member);

        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("member.regist"));

        return "redirect:/";

    }

}
