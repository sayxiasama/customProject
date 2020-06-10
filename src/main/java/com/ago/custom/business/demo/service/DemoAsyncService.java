package com.ago.custom.business.demo.service;

import com.ago.custom.business.demo.bean.vo.DemoVo;
import com.ago.custom.business.demo.dao.DemoDao;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @ClassName:DemoAsnyService
 * @Describe:
 * @Data:2020/5/2616:08
 * @Author:Ago
 * @Version 1.0
 */
@Service
@Slf4j
public class DemoAsyncService {

    private static final Logger logger = LoggerFactory.getLogger(DemoAsyncService.class);

    @Autowired
    private DemoDao dao;

    private List<String> movies =
            new ArrayList<>(
                    Arrays.asList(
                            "Forrest Gump",
                            "Titanic",
                            "Spirited Away",
                            "The Shawshank Redemption",
                            "Zootopia",
                            "Farewell ",
                            "Joker",
                            "Crawl"));

    @Async
    public CompletableFuture<List<String>> asyncSelect(String start) {
        logger.info(Thread.currentThread().getName() + "  start this Task");
        List<String> vos = movies.stream().filter(item -> item.startsWith(start)).collect(Collectors.toList());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(vos);
    }

    @Async
    public void noWaitAsyncSelect(String start) {
        logger.info(Thread.currentThread().getName() + "  start this Task");
        List<String> vos = movies.stream().filter(item -> item.startsWith(start)).collect(Collectors.toList());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
