package com.dubbo.movie.vo.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionMessageVO {

    private String id;

    private String message;

    private Integer tryCount;

    private Integer status;

    private String topic;

}
