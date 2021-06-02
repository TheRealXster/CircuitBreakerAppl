package com.example.paymentservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentServiceController {

    @GetMapping("/payment")
    public String paymentConfirmation(){
        return "Please complete the payment.";
    }
}
