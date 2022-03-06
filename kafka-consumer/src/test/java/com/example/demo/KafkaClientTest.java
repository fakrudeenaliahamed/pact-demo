package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:29092", "port=29092" })
public class KafkaClientTest {

    @Autowired
    private KafkaConsumer consumer;

    @Autowired
    private KafkaProducerService producer;


    private String topic = "sample";

    @Test
    public void kafkaTest() throws InterruptedException {
        Thread.sleep(10000);
        producer.sayHello("sample");

        Thread.sleep(10000);
        assertTrue(GreetingsDataStore.getDataStore().size()==1);



    }

}
