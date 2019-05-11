package com.example.algorithms.java8;

import com.example.algorithms.java8.stream.Dish;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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

    //找出流中是素食的所有项目
    @Test
    public void dishTest() {
        final List<Dish> collect = menu.stream().filter(Dish :: isVegetarian).collect(
                Collectors.toList());
        System.out.println(collect.toString());
    }


    //返回指定list的对数
    @Test
    public void squaresTest() {
        final List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        final List<Integer> collect = integers.stream().map(n -> n * n).collect(
                Collectors.toList());
        System.out.println(collect.toString());
    }

    //返回list1和list2的所有组合可能
    //flatmap的作用是将映射后的结果重组成一个流
    @Test
    public void pairsTest() {
        final List<Integer> num1 = Arrays.asList(1, 2, 3);
        final List<Integer> num2 = Arrays.asList(4, 5);
        final List<int[]> collect = num1.stream().flatMap(
                i -> num2.stream().map(j -> new int[]{i, j})).collect(Collectors.toList());
        for (int[] ints : collect) {
            System.out.println(ints[0] + "," + ints[1]);
        }
    }

    //在上述的基础上，返回总数能被3整除的数对
    @Test
    public void pairsTest1() {
        final List<Integer> num1 = Arrays.asList(1, 2, 3);
        final List<Integer> num2 = Arrays.asList(4, 5);
        final List<int[]> collect = num1.stream().flatMap(
                i -> num2.stream().filter(j -> (i + j) % 3 == 0).map(j -> new int[]{i, j})).collect(
                Collectors.toList());
        for (int[] ints : collect) {
            System.out.println(ints[0] + "," + ints[1]);
        }
    }

    //anyMatch allMatch noneMatch的用法
    @Test
    public void anyMatchTest() {
        if (menu.stream().anyMatch(Dish :: isVegetarian)) {
            System.out.println("the menu is vegetarian friendly!");
        }

        if (menu.stream().allMatch(dish -> dish.getCalories() < 1000)) {
            System.out.println("the menu is low calorie menu!");
        }

        if (menu.stream().noneMatch(dish -> dish.getCalories() > 1000)) {
            System.out.println("the menu has no high calories!");
        }
    }

    //reduce关键字进行规约操作 规约求和 求最大值等
    @Test
    public void reduceTest() {
        final List<Integer> num1 = Arrays.asList(1, 2, 3, 4, 5, 9);
        final Optional<Integer> reduce = num1.stream().reduce(Integer :: max);
        reduce.ifPresent(System.out :: println);

        final Integer sum = num1.stream().reduce(0, (a, b) -> a + b);
        System.out.println("sum = " + sum);

        //用map和reduce计算一共有多少道菜
        final Integer total = menu.stream().map(dish -> 1).reduce(0, (a, b) -> a + b);
        //final Integer total = menu.stream().count();
        System.out.println("total dish is = " + total);
    }
}