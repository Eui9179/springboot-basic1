package com.ll.basic1.boundedContext.member.controller;

import com.ll.basic1.base.rsdata.RsData;
import com.ll.basic1.boundedContext.member.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/login")
    public RsData login(HttpServletResponse response, @RequestParam String username, @RequestParam String password) {
        if (username == null || username.trim().length() == 0) {
            return RsData.of("F-3", "username을 입력해주세요.");
        }

        if (password == null || password.trim().length() == 0) {
            return RsData.of("F-1", "password를 입력해주세요.");
        }

        response.addCookie(new Cookie("username", username));
        return memberService.tryLogin(username, password);
    }

    @GetMapping("/me")
    public RsData getMe(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return RsData.of("F-1", "로그인 후 이용해주세요.");
        }
        String usernameInCookie = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("username"))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(null);

        return RsData.of("S-1", "당신의 username(은)는 " + usernameInCookie + "입니다.");
    }
}
