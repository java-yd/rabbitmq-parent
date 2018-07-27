package com.yd.constant.mq;

/**
 * 队列常量
 * @author yd
 *
 */
public class RabbitmqConstant {

	//简单queue
	public   static   final    String    SINGLE_QUEUE_NAME = "singleQueue";
	
	
	//--------------------延时任务----------------------
	//指定发送队列exchanges
	public   static   final    String    ORDER_EXCHANGES = "orderExchanges";//死信队列(发送队列) 交换机标识符
	//发送队列，指定队列名称
	public   static   final    String    ORDER_QUEUE_NAME = "order.queue";//死信队列(发送队列)交换机绑定键标识符
	
	
	/**
	 * 设置这两个属性就是当消息在死信队列(发送队列)中过期后，采用哪个路由发送到指定的目标队列,
	 * 过期后转发的目标队列需要绑定发送队列设置的死信路由
	 */
	public   static   final    String    DEAD_LETTER_EXCHANGE = "dlxExchanges";
	public   static   final    String    DEAD_LETTER_ROUTING_KEY = "dlxRoutingKey";//该队列消费死信的信息
}
