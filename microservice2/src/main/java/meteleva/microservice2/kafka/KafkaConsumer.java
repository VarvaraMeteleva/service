package meteleva.microservice2.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class KafkaConsumer {

    private final List<String> messages = new ArrayList<>();

    @KafkaListener(topics = "message", groupId = "my_consumer")
    public void listener(String message) {
        log.info("Received message: {}", message);
        synchronized (messages) {
            messages.add(message);
            if (messages.size() > 100) {
                messages.remove(0);
            }
        }
    }

    public List<String> getMessages() {
        synchronized (messages) {
            return new ArrayList<>(messages);
        }
    }
}
