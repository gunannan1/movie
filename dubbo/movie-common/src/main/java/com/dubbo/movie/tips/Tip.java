package com.dubbo.movie.tips;

import lombok.Data;

/**
 * 返回给前台的提示（最终转化为json形式）
 */
public abstract class Tip {

    protected int status;
    protected String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
