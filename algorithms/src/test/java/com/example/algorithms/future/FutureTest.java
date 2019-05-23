package com.example.algorithms.future;

import com.component.common.completableFuture.FutureManger;
import com.component.common.completableFuture.FutureStrategy;
import com.example.algorithms.java8.futureTest.FutureObject;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.function.Function;

public class FutureTest {

    private FutureManger<String, String, String> manger;
    private HashMap<String, Function<String, String>> hashMap = new HashMap<>();

    @Before
    public void before() {
        final FutureObject green = new FutureObject("green");
        final FutureObject yellow = new FutureObject("yellow");
        final FutureObject red = new FutureObject("red");
        final FutureObject black = new FutureObject("black");
        final FutureObject white = new FutureObject("white");
        final FutureObject grey = new FutureObject("grey");
        final FutureObject orange = new FutureObject("orange");
        hashMap.put("orange", orange);
        hashMap.put("yellow", yellow);
        hashMap.put("red", red);
        hashMap.put("black", black);
        hashMap.put("white", white);
        hashMap.put("grey", grey);
        hashMap.put("green", green);
        manger = new FutureManger<String, String, String>(hashMap);
        manger.setFutureStrategy(new FutureStrategy<String>() {
            @Override
            public boolean enable(String key) {
                return key.equals("orange") || key.equals("yellow") || key.equals("red") || key.equals("green");
            }
        });
    }

    @Test
    public void Test1() {
        manger.run("hello word");
    }
}