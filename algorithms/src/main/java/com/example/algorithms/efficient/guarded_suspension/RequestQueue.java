package com.example.algorithms.efficient.guarded_suspension;

import java.util.LinkedList;

/**
 * Created by fox.hu on 2018/6/25.
 */

public class RequestQueue {
    private LinkedList requestQueue = new LinkedList();

    public synchronized void addQueue(Request request) {
        requestQueue.add(request);
        notifyAll();
    }

    public synchronized Request getRequest() {
        while (requestQueue.size() == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return (Request) requestQueue.remove();
    }
}
