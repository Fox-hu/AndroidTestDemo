package com.component.common.completableFuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author fox.hu
 */
public class FutureManger<T, R> {
    private List<Function<T, R>> futureList;
    private FutureStrategy futureStrategy = new FutureStrategy() {};
    private ScheduledExecutorService executor;
    private FutureListener futureListener;

    public FutureManger(List<Function<T, R>> futureList) {
        this.futureList = futureList;
        //使用守护线程来提高CompletableFuture的执行效率
        executor = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors(),
                r -> {
                    Thread t = new Thread(r);
                    t.setDaemon(true);
                    return t;
                });
    }

    public void setFutureListener(FutureListener futureListener) {
        this.futureListener = futureListener;
    }

    public void run(T t) {
        final CompletableFuture[] completableFutures;
        final List<R> ret = new CopyOnWriteArrayList<>();
        completableFutures = runInternal(t).map(future -> future.thenAccept(ret :: add)).toArray(
                CompletableFuture[] ::new);
        if (futureStrategy.anyof()) {
            CompletableFuture.anyOf(completableFutures).join();
        } else {
            CompletableFuture.allOf(completableFutures).join();
        }
        //        ret = ret.stream().filter(Objects :: nonNull).collect(Collectors.toList());
        System.out.println("CompletableFuture finish, ret = " + ret);

        //todo 获取结果后返回给调用者 要清除ret
        if (this.futureListener != null) {
            futureListener.onResult(ret);
        }
    }

    private Stream<CompletableFuture<R>> runInternal(T t) {
        return futureList.stream().filter(futureStrategy :: enable).map(
                future -> CompletableFuture.supplyAsync(() -> future.apply(t), executor)
                        .applyToEitherAsync(
                                timeoutAfter(futureStrategy.getTimeoutSecond(), TimeUnit.SECONDS),
                                r -> r));
    }


    public CompletableFuture<R> timeoutAfter(long timeout, TimeUnit unit) {
        CompletableFuture<R> result = new CompletableFuture<>();
        executor.schedule(() -> result.complete(null), timeout, unit);
        return result;
    }

}
