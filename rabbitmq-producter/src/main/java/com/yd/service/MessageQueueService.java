package com.yd.service;

import com.yd.message.UserMessage;

public interface MessageQueueService {

	/**
	 * 简单发送消息到队列中
	 * @param queueName 队列名
	 * @param msg 消息内容
	 */
	void   singleSend(String queueName, String msg);
	
	/**
	 * 简单发送消息到队列中
	 * @param queueName 队列名
	 * @param msg 实体类，由于创建queue指定序列化，必须要实现序列化接口
	 */
	void   singleSend(String queueName, UserMessage userMessage);
	
	//-----------延时任务-------------
	/**
	 * 发送延时任务
	 * @param exchanges
	 * @param queueName
	 * @param msg
	 * @param timeout 过期时间，毫秒
	 */
	void   dlxSend(String  exchanges,String queueName, String msg,Long   timeout);
}
