package com.example.algorithms.future;

import com.component.common.completableFuture.FutureManger;
import com.example.algorithms.java8.futureTest.FutureObject;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FutureTest {

    private final List<Function<String,String>> inputFuture = new ArrayList<>();
    private FutureManger<String,String> manger;

    @Before
    public void before() {
        inputFuture.add(new FutureObject("green"));
        inputFuture.add(new FutureObject("yellow"));
        inputFuture.add(new FutureObject("red"));
        inputFuture.add(new FutureObject("black"));
        inputFuture.add(new FutureObject("white"));
        inputFuture.add(new FutureObject("grey"));
        inputFuture.add(new FutureObject("orange"));
        manger = new FutureManger<>(inputFuture);
    }

    @Test
    public void Test1(){
        manger.run("hello word");
    }
}