package com.example.algorithms.efficient.work_master;

/**
 * Created by fox.hu on 2018/6/20.
 */

public class PlusWorker extends Worker {
    @Override
    Object handle(Object input) {
        Integer i = (Integer) input;
        return i * i * i;
    }
}
