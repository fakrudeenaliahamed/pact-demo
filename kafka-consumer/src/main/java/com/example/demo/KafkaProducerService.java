package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, GreetingsPojoConsumer> kafkaTemplate;

    private String topic ="sample";

    public String sayHello(String message){

        kafkaTemplate.send(topic, new GreetingsPojoConsumer("key",message));
        System.out.println("Sending Message");
        return "success";
    }

}
