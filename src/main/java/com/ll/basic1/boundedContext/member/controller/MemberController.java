package com.ll.basic1.boundedContext.member.controller;

import com.ll.basic1.base.rsdata.RsData;
import com.ll.basic1.boundedContext.member.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    public MemberController() {
        memberService = new MemberService();
    }

    @GetMapping("/login")
    public RsData login(@RequestParam String username, @RequestParam String password) {
        if (username == null || username.trim().length() == 0) {
            return RsData.of("F-3", "username을 입력해주세요.");
        }

        if (password == null || password.trim().length() == 0) {
            return RsData.of("F-1", "password를 입력해주세요.");
        }

        return memberService.tryLogin(username, password);
    }
}
