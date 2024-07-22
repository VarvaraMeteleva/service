package com.meteleva.microservice.controller;


import com.meteleva.microservice.kafka.KafkaProducer;
import com.meteleva.microservice.repository.CustomerRep;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final KafkaProducer kafkaProducer;
    private final CustomerRep customerRep;

    public Controller(KafkaProducer kafkaProducer, CustomerRep customerRep) {
        this.kafkaProducer = kafkaProducer;
        this.customerRep = customerRep;
    }

    @PostMapping("/kafka/send")
        public String send(@RequestParam int id) {
        var customer = customerRep.findById(id);
        kafkaProducer.sendMessage(customer.toString());
        return "Success";
    }
}
