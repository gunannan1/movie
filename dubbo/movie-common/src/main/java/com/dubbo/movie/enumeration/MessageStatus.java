package com.dubbo.movie.enumeration;



public enum MessageStatus {
    /**
     * 消息未被消费
     */
    UNCONSUMED(0, "未被消费"),

    /**
     * 消息已被消费
     */
    CONSUMED(1, "已被消费"),


    /**
     * 超过确认消息重试次数
     */
    OVER_CONFIRM_RETRY_TIME(2, "超过确认消息重试次数"),

    /**
     * 超过消费消息重试次数
     */
    OVER_CONSUME_RETRY_TIME(3,"超过消费消息重试次数"),

    /**
     * 消息消费失败
     */
    CONSUME_FAILED(4,"消费失败"),

    /**
     * 消息被回滚
     */
    ROLLBACK(5,"已被回滚"),

    /**
     * 最终一致性方案,待发送
     */
    WAIT(6,"待发送"),

    /**
     * 最终一致性方案,已发送
     */
    DELIVERED(7,"已发送"),

    /**
     * 最终一致性方案,已完成
     */
    FINISHED(8,"已完成");



    private int code;
    private String description;
    
    MessageStatus(int code, String description) {
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
