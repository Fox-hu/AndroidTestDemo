package com.example.algorithms.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AppleTest {

    @Test
    public void filterApple() {
        final ArrayList<Apple> input = new ArrayList<>();
        input.add(new Apple("green",100));
        input.add(new Apple("yellow",200));
        input.add(new Apple("red",300));
        final List<Apple> output = Apple.filterApple(input,
                (Apple a) -> "red".equals(a.getColor()));
        System.out.print(output.toString());
    }
}