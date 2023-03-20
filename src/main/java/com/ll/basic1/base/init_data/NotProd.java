package com.ll.basic1.base.init_data;

import com.ll.basic1.boundedContext.member.service.MemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"dev", "test"})
public class NotProd {
    @Bean
    CommandLineRunner initData(MemberService memberService) {
        return args -> {
            memberService.join("user0", "0");
            memberService.join("user1", "1");
            memberService.join("user2", "2");
            memberService.join("user3", "3");
            memberService.join("user4", "4");
            memberService.join("user5", "5");
            memberService.join("user6", "6");
            memberService.join("user7", "7");
            memberService.join("user8", "8");
            memberService.join("user9", "9");
        };
    }
}
