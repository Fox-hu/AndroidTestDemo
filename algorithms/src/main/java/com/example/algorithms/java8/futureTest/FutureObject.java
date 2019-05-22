package com.example.algorithms.java8.futureTest;

import java.util.Random;
import java.util.function.Function;

public class FutureObject implements Function<String, String> {

    private String name;
    private Random random;
    private int delay;

    public FutureObject(String name) {
        this.name = name;
        this.random = new Random();
    }

    @Override
    public String apply(String s) {
        double price = applyInternal(s);
        return String.format("%s:%.2f:%d", name, price, delay);
    }


    private double applyInternal(String s) {
        delay();
        return random.nextDouble() * s.charAt(0) + s.charAt(1);
    }

    private void delay() {
        delay = 500 + new Random().nextInt(6000);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
