package com.ll.basic1.boundedContext.member.service;

import com.ll.basic1.base.RsData;

public class MemberService {
    public RsData tryLoin(String username, String password) {
        if (!username.equals("user1")) {
            return new RsData("F-2", username + "은(는) 존재하지 않은 회원입니다.");
        } else if (!password.equals("1234")) {
            return new RsData("F-1", username + "비밀번호가 일치하지 않습니다.");
        }
        return new RsData("S-1", username + "님 환영합니다.");
    }
}
