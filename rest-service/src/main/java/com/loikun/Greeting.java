package com.loikun;

public class Greeting {
    // id 表示唯一识别
    private final long id;
    // 问候的内容
    private final String content;

    public Greeting(long id, String content){
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
