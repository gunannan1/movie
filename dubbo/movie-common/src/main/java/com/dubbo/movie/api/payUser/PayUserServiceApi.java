package com.dubbo.movie.api.payUser;


public interface PayUserServiceApi {
    String payment(Integer userId, String orderId, Double money);

    int decreaseBalance(Integer userId, Double money, Integer version);

    void test();



}
