package com.yd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yd.constant.mq.RabbitmqConstant;
import com.yd.message.UserMessage;
import com.yd.service.MessageQueueService;

@Controller
@RequestMapping("message")
public class MessageController {

	@Autowired
	private   MessageQueueService   messageQueueService;
	
	@RequestMapping("singleSendMessage")
	@ResponseBody
	public   String    singleSendMessage(){
		String   msg = "第一次测试";
		messageQueueService.singleSend(RabbitmqConstant.SINGLE_QUEUE_NAME, msg);
		return  "success";
	}
	
	@RequestMapping("singleSendUserMessage")
	@ResponseBody
	public   String    singleSendUserMessage(){
		UserMessage  userMessage = new UserMessage(1, "张三");
		messageQueueService.singleSend(RabbitmqConstant.SINGLE_QUEUE_NAME, userMessage);
		return  "success";
	}
	
	//-----------延时任务-----------
	@RequestMapping("dlxSendMessage")
	@ResponseBody
	public   String    dlxSendMessage(){
		String   msg = "延时发送消息";
		messageQueueService.dlxSend(RabbitmqConstant.ORDER_EXCHANGES,RabbitmqConstant.ORDER_QUEUE_NAME, msg,9000L);
		return  "success";
	}
}
