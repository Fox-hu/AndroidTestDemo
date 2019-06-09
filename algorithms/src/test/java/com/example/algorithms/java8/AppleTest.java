package com.example.algorithms.java8;

import com.example.algorithms.java8.lambda.Apple;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class AppleTest {

    private final ArrayList<Apple> inputApple = new ArrayList<>();
    private final ArrayList<Integer> inputInteger = new ArrayList<>();

    @Before
    public void before() {
        inputApple.add(new Apple("green", 100, 10));
        inputApple.add(new Apple("yellow", 200, 20));
        inputApple.add(new Apple("red", 300, 30));

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
        filter.sort(Comparator.comparing(Apple :: getWeight).reversed()
                .thenComparing(Apple :: getHeight));
        System.out.print(filter.toString());
    }

    @Test
    public void test1() {
        //比较器复合,按重量递减排序
        inputApple.sort(Comparator.comparing(Apple :: getWeight).reversed());

        //比较器链
        //先按重量递减排序，当两个重量一致时按住高度排序
        inputApple.sort(Comparator.comparing(Apple :: getWeight).reversed()
                .thenComparing(Apple :: getHeight));
    }

    @Test
    public void test2() {
        //函数复合 两个函数的依次调用
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;

        Function<Integer, Integer> h = f.andThen(g);
        h.apply(1); //返回4

        Function<Integer, Integer> i = f.compose(g);
        i.apply(1); //返回3
    }

    @Test
    public void test3() {
        Consumer<Integer> j = integer -> System.out.println("integer j");
        Consumer<Integer> k = integer -> System.out.println("integer k");
        Consumer<Integer> integerConsumer = j.andThen(k);

        integerConsumer.accept(1);
    }


}