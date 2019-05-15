package com.example.algorithms.java8;

import com.example.algorithms.java8.completableFuture.Discount;
import com.example.algorithms.java8.completableFuture.Quote;
import com.example.algorithms.java8.completableFuture.Shop;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

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

    //使用stream来顺序查询
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

    //使用parallelStream来并行请求
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
        System.out.println(CompletableFutureTest("myPhone27S"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    //这里要分成两个流，否则会因为流的延迟效应，发向不同商家的请求只能以同步、顺序执行的方式才会成功。
    public List<String> CompletableFutureTest(String product) {
        final List<CompletableFuture<String>> collect = shops.stream().map(shop -> CompletableFuture
                .supplyAsync(() -> shop.getName() + " price is " + shop.getPrice(product))).collect(
                Collectors.toList());
        return collect.stream().map(CompletableFuture :: join).collect(Collectors.toList());
    }

    //使用改进后的CompletableFuture,自定义Executor，使用守护线程可以提高速度
    @Test
    public void test4() {
        long start = System.nanoTime();
        System.out.println(CompletableFutureImproveTest("myPhone27S"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    public List<String> CompletableFutureImproveTest(String product) {
        final List<CompletableFuture<String>> collect = shops.stream().map(shop -> CompletableFuture
                .supplyAsync(() -> shop.getName() + " price is " + shop.getPrice(product),
                        executor)).collect(Collectors.toList());
        return collect.stream().map(CompletableFuture :: join).collect(Collectors.toList());
    }

    //获取价格后再调用shop的折扣服务，两次耗时操作 都是单线程执行 需要8s
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
        List<CompletableFuture<String>> priceFutures = shops.stream().map(
                shop -> CompletableFuture.supplyAsync(() -> shop.getPriceStr(product), executor))
                .map(future -> future.thenApply(Quote :: parse)).map(future -> future.thenCompose(
                        quote -> CompletableFuture
                                .supplyAsync(() -> Discount.applyDiscount(quote), executor)))
                .collect(Collectors.toList());
        return priceFutures.stream().map(CompletableFuture :: join).collect(Collectors.toList());
    }

    //同时开启两个异步线程，取得两者的结果后进行操作
    @Test
    public void test7() {
        CompletableFuture.supplyAsync(() -> shops.get(0).getPrice("myPhone27S")).thenCombine(
                CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return 0.5;
                }), (price, rate) -> price * rate);
    }

}