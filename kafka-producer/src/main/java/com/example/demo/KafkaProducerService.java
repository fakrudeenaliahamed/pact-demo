package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, GreetingsPojoProducer> kafkaTemplate;

    private String topic ="sample";

    public String sayHello(String message){

        kafkaTemplate.send(topic, createGreetings(message));
        return "success";
    }

    public GreetingsPojoProducer createGreetings(String message){
        return new GreetingsPojoProducer("sample",message);
    }

}
