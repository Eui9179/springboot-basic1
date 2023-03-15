package com.ll.basic1.boundedContext.home;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Person {
    static int count = 0;
    private int id;
    private String name;
    private int age;

    @Builder
    public Person(String name, int age) {
        this(++count, name, age);
    }

    public void update(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
