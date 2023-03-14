package com.ll.basic1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Controller
 * 만약에 /home/main 이런 요청이 오면 아래 메서드를 실행시켜라
 */
@Controller
public class HomeController {
    @GetMapping("/home/main")
    // @ResponseBody
    // 아래 메서드를 실행한 후 그 리턴값을 Response Body에 넣어줘
    @ResponseBody
    public String showMain() {
        return "안녕하세요";
    }
}
