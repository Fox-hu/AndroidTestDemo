package com.example.algorithms.java8.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Apple {
    private String color;
    private int weight;

    public Apple(String color, int weight, int height) {
        this.color = color;
        this.weight = weight;
        this.height = height;
    }

    @Override
    public String toString() {
        return "Apple{" + "color='" + color + '\'' + ", weight=" + weight + ", height=" + height +
               '}';
    }

    private int height;


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
            if (p.test(apple)) {
                apples.add(apple);
            }
        }
        return apples;
    }

    public static <T> List<T> filter(List<T> input, Predicate<T> p) {
        final ArrayList<T> list = new ArrayList<T>();
        for (T t : input) {
            if (p.test(t)) {
                list.add(t);
            }
        }
        return list;
    }


    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
