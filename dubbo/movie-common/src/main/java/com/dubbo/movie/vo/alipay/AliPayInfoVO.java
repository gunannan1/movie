package com.dubbo.movie.vo.alipay;

import lombok.Data;

import java.io.Serializable;

@Data
public class AliPayInfoVO implements Serializable {

    private String orderId;
    private String QRCodeAddress;

}
