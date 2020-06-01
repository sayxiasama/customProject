package com.ago.custom.business.demo.controller;

import com.ago.custom.business.demo.bean.vo.DemoVo;
import com.ago.custom.business.demo.service.DemoAsyncService;
import com.ago.custom.business.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @ClassName:DemoController
 * @Describe:
 * @Data:2020/5/2615:05
 * @Author:Ago
 * @Version 1.0
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoService service;

    @Autowired
    private DemoAsyncService asyncService;

    @RequestMapping("/select")
    public List<DemoVo> selectVo() {
        return service.selectVo();
    }


    @RequestMapping("waitAsyncSelect")
    public String waitResponseAsyncSelect() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<String> words = Arrays.asList("F", "T", "S", "Z", "J", "C");
        List<CompletableFuture<List<String>>> completableFutureList = words.stream().map(word -> asyncService.asyncSelect(word)).collect(Collectors.toList());
        List<List<String>> results = completableFutureList.stream().map(CompletableFuture::join).collect(Collectors.toList());
        stopWatch.stop();
        System.out.println("run time :" + stopWatch.getTotalTimeMillis());
        return results.toString();
    }

    @RequestMapping("noWaitAsyncSelect")
    public String noWaitResponseAsyncSelect() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<String> words = Arrays.asList("F", "T", "S", "Z", "J", "C");
        words.forEach(word -> asyncService.noWaitAsyncSelect(word));
        stopWatch.stop();
        System.out.println("run time :" + stopWatch.getTotalTimeMillis());
        return "done";
    }
}
