package com.example.algorithms.efficient.guarded_suspension;

import com.example.algorithms.efficient.Data;

/**
 * Created by fox.hu on 2018/6/25.
 */

public class Request {
    String name;
    Data response;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public synchronized Data getResponse() {
        return response;
    }

    public synchronized void setResponse(Data response) {
        this.response = response;
    }

    public Request(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[ Request = " + name + "]";
    }
}
