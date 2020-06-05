package com.ago.custom.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName:AsyncTest
 * @Describe:
 * @Data:2020/6/213:30
 * @Author:Ago
 * @Version 1.0
 */
public class AsyncTest {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("小红去买酒---");
            try {
                System.out.println("小红出去, 估计5s回来");
                Thread.sleep(5000);
                return "小红回来了";
            } catch (InterruptedException e) {
                System.out.println("小红没回来");
                return "bye";
            }
        }, executor);

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("小明去买盐");
            try {
                System.out.println("小明出去买盐,估计3s回来");
                Thread.sleep(3000);
                return "小明回来了";
            } catch (InterruptedException e) {
                System.out.println("小明没回来");
                return "小明挂了";
            }
        }, executor);

        System.out.println("loading......");
        System.out.println("boring .......:(");
        System.out.println("loading ........");

        //xiaohong
        CompletableFuture<Void> voidCompletableFuture = future.thenAccept(e -> System.out.println(e));

        CompletableFuture<Void> voidCompletableFuture1 = future1.thenAccept(e -> System.out.println(e));
    }
}
