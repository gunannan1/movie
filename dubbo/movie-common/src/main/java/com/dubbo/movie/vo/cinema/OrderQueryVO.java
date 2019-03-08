package com.dubbo.movie.vo.cinema;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderQueryVO implements Serializable {

    private String cinemaId;
    private String filmPrice;

}
