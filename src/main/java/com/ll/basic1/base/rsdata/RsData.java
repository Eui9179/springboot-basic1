package com.ll.basic1.base.rsdata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RsData {
    private String resultCode;
    private String msg;
    private Object data;

    public static RsData of(String resultCode, String msg) {
        return new RsData(resultCode, msg, null);
    }

    public static RsData of(String resultCode, String msg, Object data) {
        return new RsData(resultCode, msg, data);
    }

    public boolean isSuccess() {
        return resultCode.startsWith("S-");
    }
}
