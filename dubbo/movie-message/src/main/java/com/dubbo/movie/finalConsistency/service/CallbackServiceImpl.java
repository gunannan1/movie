//package com.dubbo.movie.finalConsistency.service;
//
//import com.alibaba.dubbo.config.annotation.Service;
//import com.dubbo.movie.api.message.finalConsistency.CallbackService;
//import com.dubbo.movie.config.MQConfig;
//import com.dubbo.movie.enumeration.OrderStatus;
//import com.dubbo.movie.utils.FastJsonConvertUtil;
//import com.dubbo.movie.utils.UUIDUtil;
//import org.apache.rocketmq.client.producer.SendResult;
//import org.apache.rocketmq.common.message.Message;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
//@Service
//@Component
//public class CallbackServiceImpl implements CallbackService {
//
//
//	@Autowired
//	private SyncProducer syncProducer;
//
//	@Override
//	public void sendPayOKMessage(String orderId) {
//
//		Map<String, Object> params = new HashMap<>();
//		params.put("orderId", orderId);
//		params.put("status", OrderStatus.PAID.getCode());
//
//		String keys = UUIDUtil.genUuid() + System.currentTimeMillis();
//		Message message = new Message(MQConfig.CALLBACK_PAY_TOPIC, MQConfig.CALLBACK_PAY_TAGS, keys, FastJsonConvertUtil.convertObjectToJSON(params).getBytes());
//
//		SendResult ret = syncProducer.sendMessage(message);
//	}
//
//
//
//}
