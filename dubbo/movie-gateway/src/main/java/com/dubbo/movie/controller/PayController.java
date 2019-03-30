package com.dubbo.movie.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dubbo.movie.api.order.OrderServiceAPI;
import com.dubbo.movie.api.payUser.PayUserServiceApi;
import com.dubbo.movie.config.auth.CurrentUser;
import com.dubbo.movie.enumeration.OrderStatus;
import com.dubbo.movie.vo.ResponseVO;
import com.dubbo.movie.vo.order.OrderVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay/")

public class PayController {

    @Reference(interfaceClass = PayUserServiceApi.class, check = false)
    private PayUserServiceApi payUserServiceApi;

    @Reference(interfaceClass = OrderServiceAPI.class, check = false)
    private OrderServiceAPI orderServiceAPI;



    @RequestMapping(value = "payment",method = RequestMethod.POST)
    public ResponseVO payment(String orderId,Integer userId){

//        String userId = CurrentUser.getCurrentUser();
//        if(userId == null || userId.trim().length() == 0){
//            return ResponseVO.serviceFail("用户未登陆");
//        }



        OrderVO orderVO=orderServiceAPI.getOrderInfoById(orderId);
        if(orderVO==null){
            return ResponseVO.serviceFail("订单不存在");
        }

        //integer 为0 时 mybatis识别成了null,要么用null判断，要么改成int类型

        if(orderVO.getOrderStatus()!=0){
            if(orderVO.getOrderStatus()== OrderStatus.PAID.getCode()){
                return ResponseVO.serviceFail("订单已支付,请不要重复支付");
            }
            if(orderVO.getOrderStatus()==OrderStatus.CANCELED.getCode()){
                return ResponseVO.serviceFail("订单已取消,请重新下单");
            }
            if(orderVO.getOrderStatus()==OrderStatus.PAYING.getCode()){
                return ResponseVO.serviceFail("订单正在支付");
            }
            if(orderVO.getOrderStatus()==OrderStatus.PAY_FAILED.getCode()){
                return ResponseVO.serviceFail("该订单已支付失败");
            }
        }

        Double money=Double.valueOf(orderVO.getOrderPrice());

        String success= payUserServiceApi.payment(userId,orderId,money);

        return ResponseVO.success(success);

    }

    @RequestMapping(value = "test")
    public ResponseVO test(){
        payUserServiceApi.test();
        return ResponseVO.success("1");

    }









}
