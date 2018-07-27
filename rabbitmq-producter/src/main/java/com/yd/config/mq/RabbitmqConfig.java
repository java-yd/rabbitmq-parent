package com.yd.config.mq;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yd.constant.mq.RabbitmqConstant;


@Configuration
public class RabbitmqConfig {
	private  Exchange  exchange;

	/**
	 * 创建一个简单的queue,没有绑定到交换机(exchanges)，默认选择type:direct的(AMQP default)
	 * @return
	 */
	@Bean
	public   Queue   singleQueue(){
		return  new Queue(RabbitmqConstant.SINGLE_QUEUE_NAME,true);//第二个参数是否持久化
	}
	
	
	//------------------------------------延迟任务-----------------------------------------
	
	//指定发送队列exchanges
	@Bean("orderExchanges")
	public   DirectExchange    createExchange(){
		return  new DirectExchange(RabbitmqConstant.ORDER_EXCHANGES, true, false);
	}
	
	//发送队列
	@Bean("orderQueue")
	public Queue orderQueue() {
		Map<String, Object> arguments = new HashMap<>();
		arguments.put("x-dead-letter-exchange", RabbitmqConstant.DEAD_LETTER_EXCHANGE);
		arguments.put("x-dead-letter-routing-key", RabbitmqConstant.DEAD_LETTER_ROUTING_KEY);
		Queue queue = new Queue(RabbitmqConstant.ORDER_QUEUE_NAME,true,false,false,arguments);
		return queue; 
	}

	//把发送队列和exchanges绑定到一块
	@Bean
	public Binding  orderBinding(@Qualifier("orderQueue")Queue orderQueue,@Qualifier("orderExchanges")DirectExchange orderExchanges){
		return  BindingBuilder.bind(orderQueue).to(orderExchanges).with(RabbitmqConstant.ORDER_QUEUE_NAME);
	}
	
	//指定目標队列exchanges
	@Bean("targetExchanges")
	public   DirectExchange    createTargetExchange(){
		return  new DirectExchange(RabbitmqConstant.DEAD_LETTER_EXCHANGE, true, false);
	}	
	
	//目标队列
	@Bean("targetrQueue")
	public Queue targetrQueue() {
		Queue queue = new Queue(RabbitmqConstant.DEAD_LETTER_ROUTING_KEY,true,false,false);
		return queue; 
	}	
	
	//把目标队列和exchanges绑定到一块
	@Bean
	public Binding  targetrBinding(@Qualifier("targetrQueue")Queue targetrQueue,@Qualifier("targetExchanges")DirectExchange targetExchanges){
		return  BindingBuilder.bind(targetrQueue).to(targetExchanges).with(RabbitmqConstant.DEAD_LETTER_ROUTING_KEY);
	}	
	
}
