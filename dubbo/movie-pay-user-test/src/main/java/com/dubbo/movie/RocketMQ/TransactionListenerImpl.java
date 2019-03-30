package com.dubbo.movie.RocketMQ;

import com.dubbo.movie.enumeration.MessageStatus;
import com.dubbo.movie.enumeration.OrderStatus;
import com.dubbo.movie.service.PayUserServiceImpl;
import com.dubbo.movie.service.TransactionMessageServiceImpl;
import com.dubbo.movie.vo.message.TransactionMessageVO;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * 处理rocketmq本地事务消息
 */
@Component
public class TransactionListenerImpl implements TransactionListener {

    @Autowired
    PayUserServiceImpl payUserService;

    @Autowired
    TransactionMessageServiceImpl transactionMessageService;


    @Override
    @Transactional
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        //TODO 回滚时，如何删除消息
        //把try块里重新封装成一个事务方法？
        System.err.println("执行本地事务单元------------");
        CountDownLatch currentCountDown = null;
        Map<String, Object> params = (Map<String, Object>) arg;
        Integer userId = (Integer) params.get("userId");
        String orderId = (String) params.get("orderId");
        Double money = (Double) params.get("money");
        Double newBalance = (Double) params.get("newBalance");
        Integer currentVersion = (Integer) params.get("currentVersion");
        currentCountDown = (CountDownLatch)params.get("currentCountDown");

        try {
            //事务开始，说明已经收到rocketmq server返回的确认信息，把消息状态改为已发送。
            transactionMessageService.update(msg.getKeys(),MessageStatus.DELIVERED.getCode());


            int count= payUserService.decreaseBalance(userId,money,currentVersion);


            //更新成功，就把消息记录表中对应消息改为已完成状态，并返回commit。
            //更新失败，返回rollback，这条消息会被删除，不会发送给消费端。同时给订单服务发一条消息让他重新改变自己状态为未支付，
            //并在本地消息表中也删除该消息，防止后续冲突
            //CountDownLatch的作用是同步阻塞，只有在本地事务执行成功或者回滚之后，才通知支付服务支付成功或者支付失败
            if(count == 1) {
                transactionMessageService.update(msg.getKeys(), MessageStatus.FINISHED.getCode());
//                System.out.println(1/0);
                System.out.println("本地事务返回COMMIT_MESSAGE");
                return LocalTransactionState.COMMIT_MESSAGE;
            } else {
                transactionMessageService.delete(msg.getKeys());
                System.out.println("本地事务返回ROLLBACK_MESSAGE");
                return LocalTransactionState.ROLLBACK_MESSAGE;
            }

        }catch (Exception e){
            //如果没有用try catch，那么本地事务抛异常后会直接返回unknown状态
            e.printStackTrace();
//            transactionMessageService.delete(msg.getKeys());
//            throw new RuntimeException(e.getMessage());


            //手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("本地事务返回ROLLBACK_MESSAGE");
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
        finally {
            currentCountDown.countDown();

        }

    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        System.err.println("执行事务状态检查----------------");

        TransactionMessageVO transactionMessageVO=transactionMessageService.search(msg.getKeys());
        //如果消息状态是已发送,返回unknown
        //如果消息状态是已完成，返回COMMIT_MESSAGE
        //如果消息是别的状态或者查询出来为空，返回rollback
        if(transactionMessageVO!=null){
            if(transactionMessageVO.getStatus()==MessageStatus.DELIVERED.getCode()){
                System.out.println("事务状态检查返回UNKNOW");
                return LocalTransactionState.UNKNOW;
            }
            else if(transactionMessageVO.getStatus()==MessageStatus.FINISHED.getCode()){
                System.out.println("事务状态检查返回COMMIT_MESSAGE");
                return LocalTransactionState.COMMIT_MESSAGE;
            }
            else {
                System.out.println("事务状态检查返回ROLLBACK_MESSAGE");
                return LocalTransactionState.ROLLBACK_MESSAGE;

            }
        }
        System.out.println("事务状态检查返回ROLLBACK_MESSAGE");
        return LocalTransactionState.ROLLBACK_MESSAGE;

//        return LocalTransactionState.UNKNOW;

    }

}
