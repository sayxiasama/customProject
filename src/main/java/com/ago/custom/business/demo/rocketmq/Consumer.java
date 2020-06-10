package com.ago.custom.business.demo.rocketmq;

import com.ago.custom.business.demo.bean.vo.DemoVo;
import com.ago.custom.business.utils.ConventUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.common.message.MessageExt;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Base64;
import java.util.List;

/**
 * @ClassName:Consumer
 * @Describe:
 * @Data:2020/6/516:08
 * @Author:Ago
 * @Version 1.0
 */
public class Consumer {

    public static void main(String[] args) throws Exception {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("demoConsumerGroup");

        consumer.setNamesrvAddr("localhost:9876");

        consumer.subscribe("demo", "*");

        // callback
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                System.out.println("receive msg :" + Thread.currentThread().getName());
                for (MessageExt msg : list) {
                    System.out.println(msg.getBody().toString());
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();

        System.out.printf("Consumer Started.%n");
    }
}
