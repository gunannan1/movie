package com.dubbo.movie.enumeration;


/**
 * 用户状态
 */
public enum UserStatus {
    /**
     * 用户未激活
     */
    UNACTIVATED(0, "未激活"),

    /**
     * 用户已激活
     */
    ACTIVATED(1, "已激活"),

    /**
     * 用户被封禁
     */
    FORBIDDEN(2, "已禁用");
    private int code;
    private String description;

    UserStatus(int code, String description) {
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
