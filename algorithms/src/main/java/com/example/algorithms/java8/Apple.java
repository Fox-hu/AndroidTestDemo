package com.example.algorithms.java8;

import java.util.ArrayList;
import java.util.List;

public class Apple {
    private String color;
    private int weight;

    public Apple(String color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public static List<Apple> filterApple(List<Apple> input, AppPredicate p) {
        final ArrayList<Apple> apples = new ArrayList<>();
        for (Apple apple : input) {
            if (p.filter(apple)) {
                apples.add(apple);
            }
        }
        return apples;
    }
}
