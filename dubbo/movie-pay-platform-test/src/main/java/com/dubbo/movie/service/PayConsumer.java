package com.dubbo.movie.service;

import com.dubbo.movie.config.MQConfig;
import com.dubbo.movie.dao.PlatformBalanceMapper;
import com.dubbo.movie.model.PlatformBalance;
import com.dubbo.movie.utils.FastJsonConvertUtil;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PayConsumer {

    //设定猫眼平台id为2
    private static final int MAOYAN=2;
	
	private DefaultMQPushConsumer consumer;

	
	@Autowired
	private PlatformBalanceMapper platformBalanceMapper;
	
	private PayConsumer() {
		try {
			this.consumer = new DefaultMQPushConsumer(MQConfig.CONSUMER_GROUP_NAME);
			this.consumer.setConsumeThreadMin(10);
			this.consumer.setConsumeThreadMax(30);
			this.consumer.setNamesrvAddr(MQConfig.NAMESERVER);
			this.consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
			this.consumer.subscribe(MQConfig.TX_PAY_TOPIC, MQConfig.TX_PAY_TAGS);
			this.consumer.registerMessageListener(new MessageListenerConcurrently4Pay());
			this.consumer.start();
		} catch (MQClientException e) {
			e.printStackTrace();
		}
	}
	
	class MessageListenerConcurrently4Pay implements MessageListenerConcurrently {

		@Override
		public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
			MessageExt msg = msgs.get(0);
			try {
				String topic = msg.getTopic();
				String tags = msg.getTags();
				String keys = msg.getKeys();
				String body = new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET);
				System.err.println("收到事务消息, topic: " + topic + ", tags: " + tags + ", keys: " + keys + ", body: " + body);

				//TODO 如何进行去重幂等操作，防止多次付款？ 可以用redis或者zookeeper建立标识
				Map<String, Object> paramsBody = FastJsonConvertUtil.convertJSONToObject(body, Map.class);
				Integer userId = (Integer) paramsBody.get("userId");
				String orderId = (String)paramsBody.get("orderId");
				BigDecimal money = ((BigDecimal) paramsBody.get("money")).setScale(2, RoundingMode.HALF_UP);

                //更新平台账户，会不会有并发问题？
                PlatformBalance platformBalance = platformBalanceMapper.selectById(MAOYAN);
                platformBalance.setCurrentBalance(money.doubleValue()+platformBalance.getCurrentBalance());
                platformBalanceMapper.updateById(platformBalance);


			} catch (Exception e) {
				e.printStackTrace();
				msg.getReconsumeTimes();
				//	如果处理多次操作还是失败, 记录失败日志（做补偿 回顾 人工处理）
				return ConsumeConcurrentlyStatus.RECONSUME_LATER;
			}
			return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
		}
		
	}

	
	
	
	
	
	
	
}
