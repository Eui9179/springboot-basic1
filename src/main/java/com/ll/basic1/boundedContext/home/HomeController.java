package com.ll.basic1.boundedContext.home;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//@Controller
//만약에 '/home/main' 이런 요청이 오면 아래 메서드를 실행시켜라
@Controller
@RequestMapping("/home")
public class HomeController {
    private int count;
    private ArrayList<Person> people;

    public HomeController() {
        count = 0;
        people = new ArrayList<>();
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
    public Integer plusNumbers(
            @RequestParam(defaultValue = "0") Integer a,
            @RequestParam Integer b
    ) {
        //b 앞에 있는 어노테이션 생략 가능
        return a + b;
    }

    @GetMapping("/addPerson")
    @ResponseBody
    public String addPerson(@RequestParam String name, @RequestParam int age) {
        Person person = Person.builder()
                .name(name)
                .age(age)
                .build();
        people.add(person);
        return person.getId() + "번 사람이 추가되었습니다.";
    }

    @GetMapping("/people")
    @ResponseBody
    public List<Person> getPeople() {
        return people;
    }

    @GetMapping("/removePerson")
    @ResponseBody
    public String removePerson(@RequestParam int id) {
        boolean removed = people.removeIf(person -> person.getId() == id - 1);
        if (!removed) {
            return id + "번 회원이 존재하지 않습니다.";
        }
        return id + "번 회원 삭제 완료";
    }

    @GetMapping("/modifyPerson")
    @ResponseBody
    public List<Person> modifyPerson(@RequestParam int id, @RequestParam String name, @RequestParam int age) {
        Person person = people.get(id - 1);
        person.update(name, age);
        return people;
    }

    @GetMapping("/cookie/increase")
    @ResponseBody
    public int showCookieIncrement(HttpServletRequest request, HttpServletResponse response) {
        int ccount = 0;
        if (request.getCookies() != null) {
            ccount = Arrays.stream(request.getCookies())
                    .filter(cookie -> cookie.getName().equals("count"))
                    .map(Cookie::getValue)
                    .mapToInt(Integer::parseInt)
                    .findFirst()
                    .orElse(0);
        }
        response.addCookie(new Cookie("count", ++ccount + ""));
        return ccount;
    }


}
