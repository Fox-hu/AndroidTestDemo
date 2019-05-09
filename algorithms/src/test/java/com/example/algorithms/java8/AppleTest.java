package com.example.algorithms.java8;

import com.example.algorithms.java8.lambda.Apple;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AppleTest {

    private final ArrayList<Apple> inputApple = new ArrayList<>();
    private final ArrayList<Integer> inputInteger = new ArrayList<>();

    @Before
    public void before() {
        inputApple.add(new Apple("green", 100,10));
        inputApple.add(new Apple("yellow", 200,20));
        inputApple.add(new Apple("red", 300,30));

        inputInteger.add(100);
        inputInteger.add(200);
        inputInteger.add(300);
    }

    @Test
    public void filterApple() {
        final List<Apple> output = Apple.filterApple(inputApple,
                (Apple a) -> "red".equals(a.getColor()));
        System.out.print(output.toString());
    }

    @Test
    public void filter() {
        final List<Integer> output = Apple.filter(inputInteger, integer -> integer > 100);
        System.out.print(output.toString());

        final List<Apple> filter = Apple.filter(inputApple,
                apple -> !apple.getColor().equals("red"));
        filter.sort(Comparator.comparing(Apple::getWeight).reversed().thenComparing(Apple::getHeight));
        System.out.print(filter.toString());
    }
}