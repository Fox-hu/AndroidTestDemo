package com.example.algorithms.java8.stream;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class DishTest {
    private final List<Dish> menu = new ArrayList();

    @Before
    public void before() {
        menu.add(new Dish("pork", false, 800, Dish.Type.MEAT));
        menu.add(new Dish("beef", false, 700, Dish.Type.MEAT));
        menu.add(new Dish("chicken", false, 400, Dish.Type.MEAT));
        menu.add(new Dish("french fries", true, 530, Dish.Type.OTHER));
        menu.add(new Dish("rice", true, 350, Dish.Type.OTHER));
        menu.add(new Dish("season fruit", true, 120, Dish.Type.OTHER));
        menu.add(new Dish("pizza", true, 550, Dish.Type.MEAT));
        menu.add(new Dish("prawns", false, 300, Dish.Type.FISH));
        menu.add(new Dish("salmon", false, 450, Dish.Type.FISH));
    }

    @Test
    public void dishTest(){
        final List<Dish> collect = menu.stream().filter(Dish :: isVegetarian).collect(
                Collectors.toList());
        System.out.println(collect.toString());
    }

}