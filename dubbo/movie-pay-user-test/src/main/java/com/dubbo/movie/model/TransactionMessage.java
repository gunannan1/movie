package com.dubbo.movie.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
public class TransactionMessage {

    private String id;

    private String message;

    private Integer tryCount;

    private Integer status;

    private String topic;

    private Date nextRetry;

    private Date beginTime;

    private Date updateTime;


}
