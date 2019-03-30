//package com.dubbo.movie.finalConsistency.service;
//
//import com.alibaba.dubbo.config.annotation.Service;
//import com.dubbo.movie.api.message.finalConsistency.TransactionProducer;
//import com.dubbo.movie.config.MQConfig;
//import org.apache.rocketmq.client.exception.MQClientException;
//import org.apache.rocketmq.client.producer.TransactionMQProducer;
//import org.apache.rocketmq.client.producer.TransactionSendResult;
//import org.apache.rocketmq.common.message.Message;
//import org.apache.tomcat.util.threads.ThreadPoolExecutor;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.util.concurrent.ArrayBlockingQueue;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.ThreadFactory;
//import java.util.concurrent.TimeUnit;
//
//@Service
//@Component
//public class TransactionProducerImpl implements TransactionProducer {
//
//
//    private TransactionMQProducer producer;
//
//    private ExecutorService executorService;
//
//    @Autowired
//    TransactionListenerImpl transactionListenerImpl;
//
//
//    private TransactionProducerImpl() {
//        this.producer = new TransactionMQProducer(MQConfig.PRODUCER_GROUP_NAME);
//        //自定义线程池
//        this.executorService = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS,
//                new ArrayBlockingQueue<Runnable>(2000), new ThreadFactory() {
//            @Override
//            public Thread newThread(Runnable r) {
//                Thread thread = new Thread(r);
//                thread.setName(MQConfig.PRODUCER_GROUP_NAME + "-check-thread");
//                return thread;
//            }
//        });
//        this.producer.setExecutorService(executorService);
//        this.producer.setNamesrvAddr(MQConfig.NAMESERVER);
//
//    }
//
//
//    /**
//     * Autowired注入晚于构造器初始化，所以需要这个方法来设置transactionListenerImpl
//     * @throws Exception
//     */
//    @PostConstruct
//    public void afterPropertiesSet() throws Exception {
//        this.producer.setTransactionListener(transactionListenerImpl);
//        start();
//    }
//
//    private void start() {
//        try {
//            this.producer.start();
//        } catch (MQClientException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void shutdown() {
//        this.producer.shutdown();
//    }
//
//    @Override
//    public TransactionSendResult sendMessage(Message message, Object argument) {
//        TransactionSendResult sendResult = null;
//        try {
//            sendResult = this.producer.sendMessageInTransaction(message, argument);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return sendResult;
//    }
//
//    @Override
//    public void test(){
//        System.out.println("test");
//    }
//}
