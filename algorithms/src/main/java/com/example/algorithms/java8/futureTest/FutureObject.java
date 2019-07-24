package com.example.algorithms.java8.futureTest;

import java.util.Random;
import java.util.function.Function;

public class FutureObject implements Function<String, String> {

    private String name;
    private int delay;

    public FutureObject(String name) {
        this.name = name;
    }

    @Override
    public String apply(String s) {
        delay();
        return String.format("%s:%d", name, delay);
    }

    private void delay() {
        delay = 500 + new Random().nextInt(3000);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
