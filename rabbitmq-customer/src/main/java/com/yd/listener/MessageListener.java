package com.yd.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

	@RabbitListener(queues="singleQueue")    //监听器监听指定的Queue
    public void singleListener(String str) {
        System.out.println("singleListener:"+str);
    }
	
//	@RabbitListener(queues="singleQueue")    //监听器监听指定的Queue
//    public void singleUserMessageListener(UserMessage userMessage) {
//        System.out.println("singleUserMessageListener:"+userMessage);
//    }
	
	//延时任务
	@RabbitListener(queues="dlxRoutingKey")    //监听器监听指定的Queue
    public void dlxListener(String str) {
        System.out.println("targetrQueue:"+str);
    }
}
