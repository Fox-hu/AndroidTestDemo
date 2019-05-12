package com.example.algorithms.java8;

import org.junit.Test;

import java.util.stream.Stream;

public class StreamCreateTest {

    //生成斐波那契流，取前十
    @Test
    public void test1() {
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]}).limit(20).map(t -> t[0])
                .forEach(System.out :: println);
    }
}