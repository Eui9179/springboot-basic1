package com.ll.basic1.boundedContext.member.controller;

import com.ll.basic1.base.cookie.CookieResolver;
import com.ll.basic1.base.rsdata.RsData;
import com.ll.basic1.boundedContext.member.entity.Members;
import com.ll.basic1.boundedContext.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final String COOKIE_NAME = "loginMemberId";

    @GetMapping("/login")
    public RsData login(HttpServletResponse response, @RequestParam String username, @RequestParam String password) {
        if (username == null || username.trim().length() == 0) {
            return RsData.of("F-3", "username을 입력해주세요.");
        }

        if (password == null || password.trim().length() == 0) {
            return RsData.of("F-1", "password를 입력해주세요.");
        }

        RsData rsData = memberService.tryLogin(username, password);
        if (rsData.isSuccess()) {
            Members member = memberService.findByUsername(username);
            new CookieResolver(response).setCookie(COOKIE_NAME, member.getId());
        }

        return rsData;
    }

    @GetMapping("/me")
    public RsData getMe(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return RsData.of("F-1", "로그인 후 이용해주세요.");
        }

        long userId = new CookieResolver(request).getCookieAsLong(COOKIE_NAME, 0);
        if (userId == 0)
            return RsData.of("F-1", "로그인 후 이용해주세요.");

        Members member = memberService.findById(userId);
        return RsData.of("S-1", "당신의 id(은)는 " + member.getUsername() + "입니다.", userId);
    }

    @GetMapping("/logout")
    public RsData logout(HttpServletRequest request, HttpServletResponse response) {
        boolean deleted = new CookieResolver(request, response).removeCookie(COOKIE_NAME);
        if (deleted) {
            return RsData.of("S-1", "로그아웃되었습니다.");
        }
        return RsData.of("S-2", "이미 로그아웃되었습니다.");
    }
}
