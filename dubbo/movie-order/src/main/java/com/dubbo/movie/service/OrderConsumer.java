package com.dubbo.movie.service;


import com.dubbo.movie.config.MQConfig;
import com.dubbo.movie.dao.OrderInfoMapper;
import com.dubbo.movie.enumeration.OrderStatus;
import com.dubbo.movie.model.OrderInfo;
import com.dubbo.movie.utils.FastJsonConvertUtil;
import com.dubbo.movie.vo.order.OrderVO;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class OrderConsumer {

	private DefaultMQPushConsumer consumer;
	
	@Autowired
	private OrderServiceImpl orderServiceImpl;

	public OrderConsumer() throws MQClientException {
		consumer = new DefaultMQPushConsumer(MQConfig.CALLBACK_COMSUMER_GROUP_NAME);
        consumer.setConsumeThreadMin(10);
        consumer.setConsumeThreadMax(50);
        consumer.setNamesrvAddr(MQConfig.NAMESERVER);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        consumer.subscribe(MQConfig.CALLBACK_PAY_TOPIC, MQConfig.CALLBACK_PAY_TAGS);
        consumer.registerMessageListener(new MessageListenerConcurrently4Pay());
        consumer.start();
	}
	
	class MessageListenerConcurrently4Pay implements MessageListenerConcurrently {

		@Override
		public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        	MessageExt msg = msgs.get(0);
        	try {
				String topic = msg.getTopic();
				String msgBody = new String(msg.getBody(), "utf-8");
				String tags = msg.getTags();
				String keys = msg.getKeys();	
				System.err.println("收到消息：" + "  topic :" + topic + "  ,tags : " + tags + "keys :" + keys + ", msg : " + msgBody);
				String orignMsgId = msg.getProperties().get(MessageConst.PROPERTY_ORIGIN_MESSAGE_ID);
				System.err.println("orignMsgId: " + orignMsgId);

				Map<String, Object> body = FastJsonConvertUtil.convertJSONToObject(msgBody, Map.class);
				String orderId = (String) body.get("orderId");
				Integer status = (Integer) body.get("status");

				OrderVO orderVO=orderServiceImpl.getOrderInfoById(orderId);
				Integer currentStatus=orderVO.getOrderStatus();


				System.out.println(currentStatus+"现在订单状态");

				if(status==OrderStatus.PAID.getCode()&&currentStatus==OrderStatus.PAYING.getCode()) {
					System.err.println("订单支付成功");
					orderServiceImpl.paySuccess(orderId);
				}

				if(status==OrderStatus.PAYING.getCode()&&currentStatus==OrderStatus.UNPAID.getCode()){
					System.err.println("订单支付中......");
					orderServiceImpl.updateStatus(orderId,status);
				}

				if(status==OrderStatus.UNPAID.getCode()&&currentStatus==OrderStatus.PAYING.getCode()){
					System.err.println("支付失败，重新变为未支付状态");
					orderServiceImpl.updateStatus(orderId,status);
				}


			} catch (Exception e) {
				e.printStackTrace();
				return ConsumeConcurrentlyStatus.RECONSUME_LATER;
			}
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
		}
		
	}
	
}
