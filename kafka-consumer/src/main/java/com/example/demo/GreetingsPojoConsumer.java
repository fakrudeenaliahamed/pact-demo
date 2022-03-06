package com.example.demo;

public class GreetingsPojoConsumer {

    GreetingsPojoConsumer(String msg, String name){
        this.msg = msg;
        this.name = name;
    }



    GreetingsPojoConsumer(){
        this.msg = "";
        this.name = "";
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String msg;
    private String name;
}
