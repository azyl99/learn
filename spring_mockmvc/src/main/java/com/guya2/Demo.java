package com.guya2;

import lombok.Data;

import java.util.Date;

@Data
public class Demo {
    private final Integer id;
    private final Date time;
    private String message;

    public Demo(Integer id, Date time, String message) {
        this.id = id;
        this.time = time;
        this.message = message;
    }

    public Demo(String message) {
        this(null, null, message);
    }
}
