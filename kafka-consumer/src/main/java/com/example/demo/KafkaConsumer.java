package com.example.demo;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "sample",groupId = "foo")
    public boolean receive(GreetingsPojoConsumer message) {
        if(message.getMsg()==null || message.getName()==null){
            throw new IllegalArgumentException();
        }
        System.out.println("Received Message in group foo: " + message.getName() + message.getMsg());
        GreetingsDataStore.getDataStore().add(message);
        return true;
    }
}