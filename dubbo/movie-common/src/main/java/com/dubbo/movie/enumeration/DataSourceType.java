package com.dubbo.movie.enumeration;




public enum DataSourceType {
    /**
     * 从库
     */
    read("read", "从库"),
    /**
     * 主库
     */
    write("write", "主库");
    private String type;
    private String name;

    DataSourceType(String type, String name) {
        this.type = type;
        this.name = name;
    }
}
