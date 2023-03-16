package com.ll.basic1.boundedContext.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Members {
    private static int lastId = 0;
    private long id;
    private String username;
    private String password;

    public Members(String username, String password) {
        this.id = ++lastId;
        this.username = username;
        this.password = password;
    }
}
