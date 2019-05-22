package com.component.common.completableFuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author fox.hu
 */
public class FutureManger<T, R> {
    private List<Function<T, R>> futureList;
    private FutureStrategy futureStrategy = new FutureStrategy() {};
    private List<R> ret = new CopyOnWriteArrayList<>();
    private ScheduledExecutorService executor;

    public FutureManger(List<Function<T, R>> futureList) {
        this.futureList = futureList;
        //使用守护线程来提高CompletableFuture的执行效率
        executor = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors(),r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });
    }

    public void run(T t) {

        final CompletableFuture[] completableFutures;
        completableFutures = runInternal(t).map(future -> future.thenAccept(ret :: add)).toArray(
                CompletableFuture[] ::new);
        if (futureStrategy.anyof()) {
            CompletableFuture.anyOf(completableFutures).join();
        } else {
            CompletableFuture.allOf(completableFutures).join();
        }
        System.out.println("CompletableFuture finish, ret = " + ret);

        //todo 获取结果后返回给调用者 要清除ret

    }

    private Stream<CompletableFuture<R>> runInternal(T t) {
        return futureList.stream().filter(futureStrategy :: enable).map(
                future -> CompletableFuture.supplyAsync(() -> future.apply(t)));
    }


    public CompletableFuture<R> timeoutAfter(long timeout, TimeUnit unit) {
        CompletableFuture<R> result = new CompletableFuture<>();
        executor.schedule(() -> result.completeExceptionally(new TimeoutException()), timeout,
                unit);
        return result;
    }

}
