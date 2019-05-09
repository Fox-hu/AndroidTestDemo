package com.example.algorithms.java8.stream;

public class Dish {
    public enum Type {MEAT, FISH, OTHER}

    private final String name;
    private final boolean vegetarian;

    @Override
    public String toString() {
        return "Dish{" + "name='" + name + '\'' + ", vegetarian=" + vegetarian + ", calories=" +
               calories + ", type=" + type + '}';
    }

    private final int calories;
    private final Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }
}
