package com.example.algorithms.efficient.guarded_suspension;

/**
 * Created by fox.hu on 2018/6/25.
 */

public class Request {
    String name;

    public Request(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[ Request = " + name + "]";
    }
}
