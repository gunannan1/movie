package com.dubbo.movie.RocketMQ;

import com.dubbo.movie.config.MQConfig;
import com.dubbo.movie.enumeration.OrderStatus;
import com.dubbo.movie.utils.FastJsonConvertUtil;
import com.dubbo.movie.utils.UUIDUtil;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 本地扣款事务执行完成后，发送消息给订单模块，进行状态变更
 */
@Service
public class CallbackService {


	@Autowired
	private SyncProducer syncProducer;


	public void sendPayMessage(String orderId,int status) {
		System.err.println("发送消息，更改订单状态");

		Map<String, Object> params = new HashMap<>();
		params.put("orderId", orderId);
		params.put("status", status);
		
		String keys = UUIDUtil.genUuid() + System.currentTimeMillis();
		Message message = new Message(MQConfig.CALLBACK_PAY_TOPIC, MQConfig.CALLBACK_PAY_TAGS, keys, FastJsonConvertUtil.convertObjectToJSON(params).getBytes());
		
		syncProducer.sendMessage(message);
	}


	

	
}
