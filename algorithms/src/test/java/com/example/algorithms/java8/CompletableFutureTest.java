package com.example.algorithms.java8;

import com.example.algorithms.java8.completableFuture.Discount;
import com.example.algorithms.java8.completableFuture.Quote;
import com.example.algorithms.java8.completableFuture.Shop;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletableFutureTest {
    private List<Shop> shops;
    private Executor executor;

    @Before
    public void init() {
        shops = Arrays.asList(new Shop("BestPrice"), new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"), new Shop("BuyItAll"));

        //使用守护线程来提高CompletableFuture的执行效率
        executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });
    }

    //使用stream 顺序查询
    @Test
    public void test1() {
        long start = System.nanoTime();
        System.out.println(streamTest("myPhone27S"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    public List<String> streamTest(String product) {
        return shops.stream().map(
                shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    //使用parallelStream的并行流来进行请求
    @Test
    public void test2() {
        long start = System.nanoTime();
        System.out.println(parallelStreamTest("myPhone27S"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    public List<String> parallelStreamTest(String product) {
        return shops.parallelStream().map(
                shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    //使用CompletableFuture 异步的方法处理4个线程后将结果汇总
    @Test
    public void test3() {
        long start = System.nanoTime();
        System.out.println(completableFutureTest("myPhone27S"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    //这里要分成两个流，否则会因为流的延迟效应，发向不同商家的请求只能以同步、顺序执行的方式才会成功。
    //前一个流生成List<CompletableFuture<>>，后一个流负责收集结果 格外要注意！
    public List<String> completableFutureTest(String product) {
        final List<CompletableFuture<String>> collect = shops.stream().map(shop -> CompletableFuture
                .supplyAsync(() -> shop.getName() + " price is " + shop.getPrice(product))).collect(
                Collectors.toList());
        return collect.stream().map(CompletableFuture :: join).collect(Collectors.toList());
    }

    //使用改进后的CompletableFuture,自定义Executor，使用守护线程可以提高速度
    @Test
    public void test4() {
        long start = System.nanoTime();
        System.out.println(completableFutureImproveTest("myPhone27S"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    public List<String> completableFutureImproveTest(String product) {
        final List<CompletableFuture<String>> collect = shops.stream().map(shop -> CompletableFuture
                .supplyAsync(() -> shop.getName() + " price is " + shop.getPrice(product),
                        executor)).collect(Collectors.toList());
        return collect.stream().map(CompletableFuture :: join).collect(Collectors.toList());
    }

    //假设获取价格后，而后还需要再请求折扣服务，共两次耗时操作 都是单线程执行 需要8s
    @Test
    public void test5() {
        long start = System.nanoTime();
        System.out.println(getDiscountPrice("myPhone27S"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    private List<String> getDiscountPrice(String product) {
        return shops.stream().map(shop -> shop.getPriceStr(product)).map(Quote :: parse).map(
                Discount :: applyDiscount).collect(Collectors.toList());
    }

    //开启4个线程依次执行两次耗时操作 需要2s
    @Test
    public void test6() {
        long start = System.nanoTime();
        System.out.println(getDiscountPriceWithFuture("myPhone27S"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    //注意thenCompose的用法 getPriceStr之后执行Quote.parse方法，
    //后执行Discount.applyDiscount方法 都是在线程池中执行
    private List<String> getDiscountPriceWithFuture(String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceStr(product), executor))
                .map(future -> future.thenApply(Quote :: parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
                .collect(Collectors.toList());
        return priceFutures.stream().map(CompletableFuture :: join).collect(Collectors.toList());
    }

    //同时开启两个异步线程，取得两者的结果后进行操作
    @Test
    public void test7() {
        final CompletableFuture<Double> myPhone27S = CompletableFuture.supplyAsync(
                () -> shops.get(0).getPrice("myPhone27S")).thenCombine(
                CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return 0.5;
                }), (price, rate) -> price * rate);
        try {
            System.out.print(myPhone27S.get(2, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    //多个线程任务，等待全部返回 关键字allOf
    @Test
    public void test8() {
        long start = System.nanoTime();
        final CompletableFuture[] myPhone27S = getCompletableFuture("myPhone27S")
                .map(future -> future.thenAccept(s -> System.out.println(s + " (done in " + ((System.nanoTime() - start) / 1_000_000) + " msecs)")))
                .toArray(CompletableFuture[] ::new);
        CompletableFuture.allOf(myPhone27S).join();
        System.out.println(
                "All shops have now responded in " + ((System.nanoTime() - start) / 1_000_000) +
                " msecs");
    }

    //多个线程任务，第一个返回则返回结果 关键字anyOf
    @Test
    public void test9(){
        long start = System.nanoTime();
        final CompletableFuture[] myPhone27S = getCompletableFuture("myPhone27S").map(
                future -> future.thenAccept(s -> System.out.println(
                        s + " (done in " + ((System.nanoTime() - start) / 1_000_000) + " msecs)")))
                .toArray(CompletableFuture[] ::new);
        CompletableFuture.anyOf(myPhone27S).join();
        System.out.println("first shop have now responded in " + ((System.nanoTime() - start) / 1_000_000) + " msecs");
    }

    private Stream<CompletableFuture<String>> getCompletableFuture(String product) {
        return shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceStr(product), executor))
                .map(future -> future.thenApply(Quote :: parse))
                .map(future -> future.thenCompose(
                        quote -> CompletableFuture
                                .supplyAsync(() -> Discount.applyDiscount(quote))));
    }
}