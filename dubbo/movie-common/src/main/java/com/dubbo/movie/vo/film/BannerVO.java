package com.dubbo.movie.vo.film;



import lombok.Data;

import java.io.Serializable;

/**
 * 图片
 */
@Data
public class BannerVO implements Serializable {

    private String bannerId;
    private String bannerAddress;
    private String bannerUrl;

    // getter 和 setter方法，toString方法

}
