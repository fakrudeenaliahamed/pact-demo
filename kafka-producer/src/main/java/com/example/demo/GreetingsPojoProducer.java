package com.example.demo;

public class GreetingsPojoProducer {

    GreetingsPojoProducer(String msg, String name){
        this.msg = msg;
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    GreetingsPojoProducer(){
        this.msg = "";
        this.name = "";
    }

    private String msg;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
