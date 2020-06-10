package com.ago.custom;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName:LettuceTest
 * @Describe:
 * @Data:2020/6/916:34
 * @Author:Ago
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LettuceTest {

    @Autowired
    private StatefulRedisConnection redisConnection;

    @Test
    public void testLettuce() {

        RedisURI redisURI = RedisURI.builder().withHost("localhost").withPort(6379).withDatabase(1).build();

        RedisClient redisClient = RedisClient.create(redisURI);

        StatefulRedisConnection<String, String> connection = redisClient.connect();

        RedisCommands<String, String> redisCommands = connection.sync();

        SetArgs args = SetArgs.Builder.nx().ex(5);

        String result = redisCommands.set("hello lettuce", "hello !!!", args);
        System.out.println(result);
        Assertions.assertThat(result).isEqualToIgnoringCase("OK");

        result = redisCommands.get("hello lettuce");

        Assertions.assertThat(result).isEqualTo("hello !!!");
        System.out.println(result);
        connection.close();

        redisClient.shutdown();
    }


    @Test
    public void testLettuceStart(){

        RedisCommands redisCommands = redisConnection.sync();

        SetArgs args = SetArgs.Builder.nx().ex(10);

        String result = redisCommands.set("ago", "hello lettuce starter");
        Assertions.assertThat(result).isEqualToIgnoringCase("OK");

        String ago =(String) redisCommands.get("ago");

        System.out.println(ago);
    }


}
