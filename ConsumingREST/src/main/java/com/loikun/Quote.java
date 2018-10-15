package com.loikun;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 用于存放所需要的数据
 * JsonIgnoreProperties注解配置 “忽略未知类型”（没有绑定的类型）
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {
    // 数据类型
    private String type;
    // 数据体
    private Value value;

    public Quote() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "type='" + type + '\'' +
                ", value=" + value +
                '}';
    }
}
