package meteleva.microservice2.controller;

import meteleva.microservice2.kafka.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageController {

    private final KafkaConsumer kafkaConsumer;

    @Autowired
    public MessageController(KafkaConsumer kafkaConsumer) {
        this.kafkaConsumer = kafkaConsumer;
    }

    @GetMapping("/api/messages")
    public List<String> getMessages() {
        return kafkaConsumer.getMessages();
    }
}
