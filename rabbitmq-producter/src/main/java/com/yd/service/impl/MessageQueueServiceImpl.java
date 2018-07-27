package com.yd.service.impl;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.message.UserMessage;
import com.yd.service.MessageQueueService;

@Service
public class MessageQueueServiceImpl implements MessageQueueService {

	@Autowired
	private  RabbitTemplate   rabbitTemplate;
	
	/**
	 * 简单发送，未指定exchanges(未指定exchanges，type是：direct)
	 */
	public   void   singleSend(String queueName, String msg){
		//1：不指定exchanges时：默认会选择(AMQP default)
		//2：发送到指定queue时，当前queue必须要存在，如果不指定routing_key默认和queue名称一样
		rabbitTemplate.convertAndSend(queueName, msg);
	}
	
	
	/**
	 * 简单发送，未指定exchanges(未指定exchanges，type是：direct)
	 * 但是是同一个队列，但是传输的数据类型不一样，最好都是String(对象使用json串)
	 */
	public   void   singleSend(String queueName, UserMessage userMessage){
		rabbitTemplate.convertAndSend(queueName, userMessage);
	}

	
	
	//-----------延时任务-------------
	/**
	 * 发送延时任务
	 * @param exchanges
	 * @param queueName
	 * @param msg
	 */
	@Override
	public void dlxSend(String exchanges, String queueName, String msg,final Long   timeout) {
		rabbitTemplate.convertAndSend(exchanges, queueName, msg, new MessagePostProcessor() {
			//设置消息过期时间
			@Override
			public Message postProcessMessage(Message message) throws AmqpException {
				message.getMessageProperties().setExpiration(timeout+"");
				return message;
			}
		});
	}
	
}
