package com.ll.basic1.boundedContext.member.controller;

import com.ll.basic1.base.cookie.CookieResolver;
import com.ll.basic1.base.rsdata.RsData;
import com.ll.basic1.base.session.SessionResolver;
import com.ll.basic1.boundedContext.member.entity.Members;
import com.ll.basic1.boundedContext.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final CookieResolver cookieResolver;
    private final SessionResolver sessionResolver;

    private final String COOKIE_NAME = "loginMemberId";
    private final String SESSION_NAME = "loginMemberId";

    @GetMapping("/login")
    public String showLoginForm() {
        if (sessionResolver.isLogin(SESSION_NAME)) {
            return "usr/member/allogin.html";
        }
        return "usr/member/login.html";
    }

    @PostMapping("/login")
    @ResponseBody
    public RsData login(@RequestParam String username, @RequestParam String password) {
        if (username == null || username.trim().length() == 0) {
            return RsData.of("F-3", "username을 입력해주세요.");
        }

        if (password == null || password.trim().length() == 0) {
            return RsData.of("F-1", "password를 입력해주세요.");
        }

        RsData rsData = memberService.tryLogin(username, password);
        if (rsData.isSuccess()) {
            Members member = memberService.findByUsername(username);
            sessionResolver.setSession(SESSION_NAME, member.getId());
//            cookieResolver.setCookie(COOKIE_NAME, member.getId());
        }
        return rsData;
    }

    @GetMapping("/logout")
    @ResponseBody
    public RsData logout() {
//        boolean deleted = cookieResolver.removeCookie(COOKIE_NAME);
        boolean deleted = sessionResolver.removeSession(SESSION_NAME);
        if (deleted) {
            return RsData.of("S-1", "로그아웃되었습니다.");
        }
        return RsData.of("S-2", "이미 로그아웃되었습니다.");
    }

    @GetMapping("/me")
    public String getMe(Model model) {
//        if (request.getCookies() == null) {
//            return RsData.of("F-1", "로그인 후 이용해주세요.");
//        }
//        long userId = cookieResolver.getCookieAsLong(COOKIE_NAME, 0);
        long memberId = sessionResolver.getSessionAsLong(SESSION_NAME, 0);

//        if (memberId == 0)
//            return RsData.of("F-1", "로그인 후 이용해주세요.");

        Members member = memberService.findById(memberId);

        model.addAttribute("member", member);
        System.out.println("member = " + member);
//        return RsData.of("S-1", "당신의 id(은)는 " + member.getUsername() + "입니다.", memberId);
        return "usr/member/me.html";
    }
}
