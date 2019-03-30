package com.dubbo.movie.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.dubbo.movie.RocketMQ.CallbackService;
import com.dubbo.movie.RocketMQ.TransactionProducer;
import com.dubbo.movie.api.payUser.PayUserServiceApi;
import com.dubbo.movie.config.MQConfig;
import com.dubbo.movie.dao.UserBalanceMapper;
import com.dubbo.movie.enumeration.MessageStatus;
import com.dubbo.movie.enumeration.OrderStatus;
import com.dubbo.movie.model.UserBalance;
import com.dubbo.movie.utils.FastJsonConvertUtil;
import com.dubbo.movie.vo.message.TransactionMessageVO;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

@Component
@Service
public class PayUserServiceImpl implements PayUserServiceApi {

    @Autowired
    UserBalanceMapper userBalanceMapper;

    @Autowired
    TransactionProducer transactionProducer;

    @Autowired
    CallbackService callbackService;

    @Autowired
    TransactionMessageServiceImpl transactionMessageService;

    @Override
    public String payment(Integer userId, String orderId, Double money) {
        String result = "";

        try {

            //获取用户当前账户余额与版本
            UserBalance userBalance =userBalanceMapper.selectById(userId);
            Integer currentVersion=userBalance.getVersion();
            Double currentBalance=userBalance.getCurrentBalance();

            Double newBalance=currentBalance-money;

            //如果有足够余额，则组装消息进行付款
            if(newBalance>0){

                //消息id
                String keys = orderId + userId;
                Map<String, Object> params = new HashMap<>();
                params.put("userId", userId);
                params.put("orderId", orderId);
                params.put("money", money);
                Message message = new Message(MQConfig.TX_PAY_TOPIC, MQConfig.TX_PAY_TAGS, keys, FastJsonConvertUtil.convertObjectToJSON(params).getBytes());

                //保存一条消息记录到数据库，方便回查事务执行状态.如果该消息存在,说明这笔支付还在处理，直接返回。
                TransactionMessageVO transactionMessageVO=transactionMessageService.search(keys);
                if(transactionMessageVO==null){
                    transactionMessageVO= new TransactionMessageVO(keys,"支付消息",0, MessageStatus.WAIT.getCode(),MQConfig.TX_PAY_TOPIC);
                    transactionMessageService.save(transactionMessageVO);

                }
                else {
                    result="支付正在进行中或者已经完成，请不要重复尝试";
                    return result;
                }


                //通知订单改变状态为正在支付中，防止用户重复支付
                callbackService.sendPayMessage(orderId, OrderStatus.PAYING.getCode());

                params.put("newBalance", newBalance);
                params.put("currentVersion", currentVersion);

                //	同步阻塞,等待本地事务执行完成
                CountDownLatch countDownLatch = new CountDownLatch(1);
                params.put("currentCountDown", countDownLatch);


                //发送消息并执行本地事务

                TransactionSendResult sendResult =  transactionProducer.sendMessage(message,params);
                System.out.println(sendResult.getLocalTransactionState().toString());

                countDownLatch.await();

                System.out.println("等待结束");


                //如果支付成功，则通知订单服务模块改变订单状态为支付成功
                if(sendResult.getSendStatus() == SendStatus.SEND_OK
                        && sendResult.getLocalTransactionState() == LocalTransactionState.COMMIT_MESSAGE) {
                    callbackService.sendPayMessage(orderId,OrderStatus.PAID.getCode());
                    result = "支付成功!";
                } else {
                    //TODO 如果本地事务由于出异常而返回，这里是否需要查询修改订单和消息的状态，以免不能再次支付
                    callbackService.sendPayMessage(orderId, OrderStatus.UNPAID.getCode());

                    result = "支付失败!";
                }

            }else {
                result = "余额不足!";
            }

        }catch (Exception e){
            e.printStackTrace();
            result = "支付失败!";
            return result;
        }
        return result;


    }

    /**
     * 更新账户余额
     * @param userId 用户id
     * @param money 扣款
     * @param version 版本号
     * @return
     */
    @Override
    public int decreaseBalance(Integer userId, Double money, Integer version) {
        return userBalanceMapper.decreaseBalance(userId,version,money);
    }


    @Override
    public void test(){
        System.out.println("sss");
    }







}
