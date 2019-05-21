package com.component.common.completableFuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Stream;

public class FutureManger {
    private List<Function> futureList;
    private FutureStrategy futureStrategy = new FutureStrategy() {};

    public FutureManger(List<Function> futureList) {this.futureList = futureList;}

    public void run() {
        final CompletableFuture[] completableFutures = runInternal().map(
                future -> future.thenAccept(System.out :: println)).toArray(
                CompletableFuture[] ::new);
        if (futureStrategy.anyof()) {
            CompletableFuture.anyOf(completableFutures).join();
        } else {
            CompletableFuture.allOf(completableFutures).join();
        }
    }

    private Stream<CompletableFuture<Function>> runInternal() {
        return futureList.stream().filter(futureStrategy :: enable).map(
                future -> CompletableFuture.supplyAsync(() -> future));
    }
}
