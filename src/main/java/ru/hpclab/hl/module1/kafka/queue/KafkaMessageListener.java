package ru.hpclab.hl.module1.kafka.queue;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.hpclab.hl.module1.model.KafkaOperationMessage;


@Component
@RequiredArgsConstructor
public class KafkaMessageListener {

    private final ObjectMapper objectMapper;

    private final KafkaMessageDispatcher kafkaMessageDispatcher;

    private static final Logger log = LoggerFactory.getLogger(KafkaMessageListener.class);

    @KafkaListener(
            topics = "${kafka.topic:var15}",
            groupId = "${kafka.groupId:esecretareva-consumer-group}",
            concurrency = "${kafka.concurrency:2}"
    )
    public void handleMessage(String messageJson) {
        try {
            KafkaOperationMessage message = objectMapper.readValue(messageJson, KafkaOperationMessage.class);
            kafkaMessageDispatcher.dispatch(message);
        } catch (Exception e) {
            log.error("Error while parsing or dispatching Kafka message: {}", messageJson, e);
        }
    }
}
