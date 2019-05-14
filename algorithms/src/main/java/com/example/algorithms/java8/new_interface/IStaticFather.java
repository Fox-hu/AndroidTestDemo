package com.example.algorithms.java8.new_interface;

public interface IStaticFather {
    default void init(){

    }

    static void test(){
        System.out.println("IStaticFather test");
    }
}
