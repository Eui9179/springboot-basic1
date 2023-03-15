package com.ll.basic1.boundedContext.member.service;

import com.ll.basic1.base.rsdata.RsData;
import com.ll.basic1.boundedContext.member.entity.Members;
import com.ll.basic1.boundedContext.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public RsData tryLogin(String username, String password) {
        Members member = memberRepository.findByUsername(username);
        if (member == null) {
            return RsData.of("F-2", username + "은(는) 존재하지 않은 회원입니다.");
        } else if (!member.getPassword().equals(password)) {
            return RsData.of("F-1", username + "비밀번호가 일치하지 않습니다.");
        }
        return RsData.of("S-1", username + "님 환영합니다.");
    }

    public void addMember(String username, String password) {
        memberRepository.addMember(new Members(username, password));
    }
}
