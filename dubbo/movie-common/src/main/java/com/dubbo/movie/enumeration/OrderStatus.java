package com.dubbo.movie.enumeration;


/**
 * 订单状态
 */
public enum OrderStatus {

    /**
     * 订单未支付
     */
    UNPAID(0, "未付款"),

    /**
     * 订单已支付
     */
    PAID(1, "已付款"),

    /**
     * 用户取消订单
     */
    CANCELED(2, "取消"),

    /**
     * 订单正在付款中
     */
    PAYING(3, "正在付款"),

    /**
     * 订单付款失败
     */
    PAY_FAILED(4, "付款失败");


    private int code;
    private String description;

    OrderStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
