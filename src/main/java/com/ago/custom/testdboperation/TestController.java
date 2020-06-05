package com.ago.custom.testdboperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName:TestController
 * @Describe:
 * @Data:2020/6/510:24
 * @Author:Ago
 * @Version 1.0
 */
@RestController
@RequestMapping("/testAsync")
public class TestController {

    private ExecutorService executor = Executors.newFixedThreadPool(2);

    @Autowired
    private TestDbOperation dao;

    @RequestMapping("/test")
    public String AsyncDemo() {
        CompletableFuture<String> future = null;
            future = CompletableFuture.supplyAsync(() -> {
                System.out.println("async start db operation");
                try {
                    dao.AsyncSave();
                    return "async save success";
                } catch (Exception e) {
                    e.printStackTrace();
                    return "dead";
                }
            }, executor);
            future.thenAccept((e) -> System.out.println(e));
            return "success";
        }
    }
