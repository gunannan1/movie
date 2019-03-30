package com.dubbo.movie.config;

public class MQConfig {
     public static final String NAMESERVER ="10.0.0.39:9876";

     public static final String PRODUCER_GROUP_NAME = "tx_pay_producer_group_name";

     public static final String CONSUMER_GROUP_NAME = "tx_pay_consumer_group_name";

     public static final String TX_PAY_TOPIC = "tx_pay_topic";

     public static final String TX_PAY_TAGS = "pay";

     public static final String CALLBACK_PAY_TOPIC = "callback_pay_topic";

     public static final String CALLBACK_PAY_TAGS = "callback_pay";

     public static final String CALLBACK_PRODUCER_GROUP_NAME = "callback_pay_producer_group_name";

     public static final String CALLBACK_COMSUMER_GROUP_NAME = "callback_pay_consumer_group_name";


}
