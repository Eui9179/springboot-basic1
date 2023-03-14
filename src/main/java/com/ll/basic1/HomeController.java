package com.ll.basic1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


//@Controller
//만약에 '/home/main' 이런 요청이 오면 아래 메서드를 실행시켜라
@Controller
@RequestMapping("/home")
public class HomeController {
    private int count;

    public HomeController() {
        count = 0;
    }

    @GetMapping("/main")
    // @ResponseBody
    // 아래 메서드를 실행한 후 그 리턴값을 Response Body에 넣어줘
    @ResponseBody
    public String showMain() {
        return "안녕하세요";
    }

    @GetMapping("/increase")
    @ResponseBody
    public Integer increaseCount() {
        return count++;
    }

    @GetMapping("/plus")
    @ResponseBody
    public Integer plusNumbers(@RequestParam(defaultValue = "0") Integer a, @RequestParam Integer b) {
        //b 앞에 있는 어노테이션 생략 가능
        return a + b;
    }
}
