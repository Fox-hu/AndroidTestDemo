package com.example.algorithms.java8;

import com.example.algorithms.java8.new_interface.IStaticFather;
import com.example.algorithms.java8.new_interface.IStaticSon;

import org.junit.Test;

public class InterfaceTest {
    @Test
    public void test(){
        IStaticFather.test();
        IStaticSon.test();
    }
}