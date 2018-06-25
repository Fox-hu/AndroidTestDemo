package com.example.algorithms.efficient.work_master;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by fox.hu on 2018/6/20.
 */

public class Master {
    private static final String TAG = Master.class.getSimpleName();

    Queue<Object> workQueue = new ConcurrentLinkedQueue<>();

    Map<String, Thread> threadMap = new ConcurrentHashMap<>();

    Map<String, Object> resultMap = new ConcurrentHashMap<>();

    Master(Worker worker, int threadCount) {
        worker.setWorkQueue(workQueue);
        worker.setResultMap(resultMap);
        for (int i = 0; i < threadCount; i++) {
            threadMap.put(String.valueOf(i), new Thread(worker, String.valueOf(i)));
        }
    }

    public void submit(Object obj) {
        workQueue.add(obj);
    }

    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    public void execute() {
        for (Map.Entry<String, Thread> stringThreadEntry : threadMap.entrySet()) {
            stringThreadEntry.getValue().start();
        }
    }

    public boolean isComplete() {
        for (Map.Entry<String, Thread> stringThreadEntry : threadMap.entrySet()) {
            if (stringThreadEntry.getValue().getState() != Thread.State.TERMINATED) {
                return false;
            }
        }
        return true;
    }
}
