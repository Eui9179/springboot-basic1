package com.ll.basic1.boundedContext.member;

import com.ll.basic1.base.RsData;
import com.ll.basic1.boundedContext.member.service.MemberService;
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

    @GetMapping("/login")
    public RsData login(@RequestParam String username, @RequestParam String password) {
        return memberService.tryLoin(username, password);
    }
}
