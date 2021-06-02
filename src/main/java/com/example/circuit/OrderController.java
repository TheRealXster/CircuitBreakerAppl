package com.example.circuit;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

    private static final String ORDER_SERVICE = "orderService";
    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @RequestMapping("/order")
    @CircuitBreaker(name = ORDER_SERVICE, fallbackMethod = "orderFallBack")
    public ResponseEntity<String> makeAnOrder(){
        System.out.println("Im reaching here");
        String response = restTemplate.getForObject("http://localhost:8081/payment", String.class);
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    public ResponseEntity<String> orderFallBack(Exception e){
        return new ResponseEntity<String>("Payment service is down", HttpStatus.OK);
    }
}
