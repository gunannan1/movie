//package com.dubbo.movie.finalConsistency.service;
//
//import com.alibaba.dubbo.config.annotation.Reference;
//import com.alibaba.dubbo.config.annotation.Service;
//import com.dubbo.movie.api.payUser.PayUserServiceApi;
//import org.apache.rocketmq.client.producer.LocalTransactionState;
//import org.apache.rocketmq.client.producer.TransactionListener;
//import org.apache.rocketmq.common.message.Message;
//import org.apache.rocketmq.common.message.MessageExt;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//import java.util.concurrent.CountDownLatch;
//
//@Service
//@Component
//public class TransactionListenerImpl implements TransactionListener {
//
//    @Reference(interfaceClass = PayUserServiceApi.class, check = false)
//    PayUserServiceApi payUserServiceApi;
//
//    @Override
//    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
//        //TODO 会不会有重复支付问题？
//        System.err.println("执行本地事务单元------------");
//        CountDownLatch currentCountDown = null;
//
//
//        try {
//            Map<String, Object> params = (Map<String, Object>) arg;
//            Integer userId = (Integer) params.get("userId");
//            String orderId = (String) params.get("orderId");
//            Double money = (Double) params.get("money");
//            Double newBalance = (Double) params.get("newBalance");
//            Integer currentVersion = (Integer) params.get("currentVersion");
//            currentCountDown = (CountDownLatch)params.get("currentCountDown");
//
//
//
//            int count= payUserServiceApi.decreaseBalance(userId,money,currentVersion);
//
//            //更新成功，返回commit。更新失败，返回rollback，这条消息会被删除，不会发送给消费端。
//            //CountDownLatch，只有在本地事务执行成功或者回滚之后，才通知支付服务支付成功或者支付失败
//            if(count == 1) {
//                currentCountDown.countDown();
//                return LocalTransactionState.COMMIT_MESSAGE;
//            } else {
//                currentCountDown.countDown();
//                return LocalTransactionState.ROLLBACK_MESSAGE;
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//            currentCountDown.countDown();
//            return LocalTransactionState.ROLLBACK_MESSAGE;
//        }
//
//    }
//
//    @Override
//    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
//        System.err.println("执行事务状态检查----------------");
//
//        //todo 根据数据库的订单状态，返回LocalTransactionState
//        return LocalTransactionState.COMMIT_MESSAGE;
//    }
//
//}
