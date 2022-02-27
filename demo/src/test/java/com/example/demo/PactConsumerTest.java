package com.example.demo;


import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.loader.PactBrokerAuth;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(PactConsumerTestExt.class)
public class PactConsumerTest {

    @LocalServerPort
    int port;

    private Map<String, String> headers() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "text/plain; charset=utf-8");
        return headers;
    }


    @Pact(consumer = "myapp", provider = "demoService")
    RequestResponsePact getDemo(PactDslWithProvider builder) {

        return builder.given("sample demo")
                .uponReceiving("call demo")
                .method("GET")
                .path("/demoprovider")
                .willRespondWith()
                .status(200)
                .headers(headers())
                .body("sample").toPact();

    }

    @Test
    @PactTestFor(pactMethod = "getDemo",port = "9090")
    void getAllProducts_whenProductsExist(MockServer mockServer) {
        RestTemplate template = new RestTemplateBuilder().rootUri("http://localhost:"+port+"/").build();
        String result = template.getForObject("/hello",String.class);
        assertTrue(result.contains("sample"));

    }


}
