package com.example.algorithms.multiple_thread.producer_customer;

import java.util.concurrent.BlockingQueue;

/**
 * Created by fox.hu on 2018/11/9.
 */

public class BlockQueueChannel<P> implements Channel<P> {
    final private BlockingQueue<P> blockingQueue;

    public BlockQueueChannel(BlockingQueue<P> blockingQueue) {this.blockingQueue = blockingQueue;}

    @Override
    public P take() throws InterruptedException  {
        return blockingQueue.take();
    }

    @Override
    public void put(P product) throws InterruptedException  {
        blockingQueue.put(product);
    }

    @Override
    public int size() throws InterruptedException  {
        return blockingQueue.size();
    }
}
