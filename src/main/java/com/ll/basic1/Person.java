package com.ll.basic1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {
    static int count = 0;
    private int id;
    private String name;
    private int age;

    @Builder
    public Person(String name, int age) {
        this.id = ++count;
        this.name = name;
        this.age = age;
    }
}
