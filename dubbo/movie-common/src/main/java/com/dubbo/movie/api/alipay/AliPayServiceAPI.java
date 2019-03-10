package com.dubbo.movie.api.alipay;


import com.dubbo.movie.vo.alipay.AliPayInfoVO;
import com.dubbo.movie.vo.alipay.AliPayResultVO;

public interface AliPayServiceAPI {

    AliPayInfoVO getQRCode(String orderId);

    AliPayResultVO getOrderStatus(String orderId);

}
