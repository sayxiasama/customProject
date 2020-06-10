package com.ago.custom.business.demo.rocketmq;

import com.ago.custom.business.demo.bean.vo.DemoVo;
import com.ago.custom.business.utils.ConventUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName:SynProducer
 * @Describe:
 * @Data:2020/6/516:02
 * @Author:Ago
 * @Version 1.0
 */
public class SynProducer {

    /*public static void main(String[] args) throws  Exception {
        DefaultMQProducer producer = new DefaultMQProducer("demoProducerGroup");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        for(int i = 0 ; i<20; i++){
            //create message .  topic , tag
//            Message message = new Message("demo", "tag", ("hello rocketMq :" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
        Message message = new Message("demo", "tag", ConventUtils.objectToByteArray(new DemoVo("code"+i, "key"+i)));
        SendResult sendResult = producer.send(message);
            System.out.printf("%s%n",message.toString());
        }
        producer.shutdown();
    }*/


    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("demoAsyncGroup");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();

        int messageCount = 15;

        CountDownLatch countDownLatch = new CountDownLatch(messageCount);
        for (int i = 0; i < messageCount; i++) {
            final int index = i;

            Message msg = new Message("demo", "TagA", "hello asyncSend".getBytes(RemotingHelper.DEFAULT_CHARSET));
            producer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("ok" + sendResult);
                }

                @Override
                public void onException(Throwable e) {
                    System.out.println("exception");
                    e.printStackTrace();
                }
            });
        }

        countDownLatch.await(2, TimeUnit.SECONDS);

        producer.shutdown();

    }
}
