package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    KafkaProducerService demoService;

    @GetMapping("/hello/{name}")
    String all(@PathVariable String name) {
        return demoService.sayHello(name);
    }
}
