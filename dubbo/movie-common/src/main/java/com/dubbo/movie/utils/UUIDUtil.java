package com.dubbo.movie.utils;

import java.util.UUID;

public class UUIDUtil {

    public static String genUuid(){
        return UUID.randomUUID().toString().replace("-","");
    }

}
