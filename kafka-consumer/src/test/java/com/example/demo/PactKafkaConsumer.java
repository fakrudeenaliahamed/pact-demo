package com.example.demo;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import au.com.dius.pact.consumer.MessagePactBuilder;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.consumer.junit5.ProviderType;
import au.com.dius.pact.core.model.messaging.Message;
import au.com.dius.pact.core.model.messaging.MessagePact;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "kafka-provider", providerType = ProviderType.ASYNCH)
public class PactKafkaConsumer {

//    @Autowired
//    KafkaConsumer kafkaConsumer;

    @Pact(consumer = "kafka-consumer")
    MessagePact createPact(MessagePactBuilder builder) {
        PactDslJsonBody body = new PactDslJsonBody();
        body.stringType("msg", null);
        body.stringType("name", "fakrudeen");


        Map<String, Object> metadata = new HashMap<>();
        metadata.put("Content-Type", "application/json");
        metadata.put("kafka_topic", "sample");

        return builder.expectsToReceive("message with null sent").withMetadata(metadata).withContent(body).toPact();
    }

    @Test
    @PactTestFor(pactMethod = "createPact")
    public void test(List<Message> messages) throws Exception {
        KafkaConsumer kafkaConsumer = new KafkaConsumer();
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("Message received -> " + messages.get(0).contentsAsString());
        GreetingsPojoConsumer greetings = mapper.readValue(messages.get(0).contentsAsString(), GreetingsPojoConsumer.class);

        assertDoesNotThrow(() -> {
            kafkaConsumer.receive(greetings);
        });
    }
}