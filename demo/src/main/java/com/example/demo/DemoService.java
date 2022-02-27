package com.example.demo;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DemoService {

    public String sayHello(){
        RestTemplate template = new RestTemplateBuilder().rootUri("http://localhost:9090").build();
        return template.getForObject("/demoprovider",String.class);
    }

}
