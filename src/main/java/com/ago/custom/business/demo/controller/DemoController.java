package com.ago.custom.business.demo.controller;

import com.ago.custom.business.demo.bean.vo.DemoVo;
import com.ago.custom.business.demo.service.DemoAsyncService;
import com.ago.custom.business.demo.service.DemoService;
import com.ago.custom.redisrelation.utils.ApplicationContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;
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

    @Autowired
    private RedisTemplate redisTemplate;


//    private  static RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextHolder.getInstance().getBean("redisTemplate", RedisTemplate.class);

    @RequestMapping("/select")
    public List<DemoVo> selectVo(String index) {
        System.out.println(index);
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

    @RequestMapping("/redis")
    public String testRedis() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        DemoVo vo = new DemoVo("device999", "0002");
        vo.setDateStr(date);
        redisTemplate.opsForHash().put(vo.getCode(), date, vo);
        Map entries = redisTemplate.opsForHash().entries(vo.getCode());
        Set set = entries.entrySet();
        if (set != null) {
            Iterator iterator = set.iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                Object key = entry.getKey();
                DemoVo value = (DemoVo) entry.getValue();
                System.out.println(key);
                System.out.println(value.toString());
            }
        }
        return "success";
    }

}
