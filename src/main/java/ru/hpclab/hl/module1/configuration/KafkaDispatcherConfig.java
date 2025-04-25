package ru.hpclab.hl.module1.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hpclab.hl.module1.kafka.queue.KafkaMessageDispatcher;
import ru.hpclab.hl.module1.service.CourierService;
import ru.hpclab.hl.module1.service.DeliveryService;
import ru.hpclab.hl.module1.service.ParcelService;

@Configuration
public class KafkaDispatcherConfig {
    @Bean
    public KafkaMessageDispatcher kafkaMessageDispatcher(
            CourierService courierService,
            DeliveryService deliveryService,
            ParcelService parcelService,
            ObjectMapper objectMapper
    ) {
        return new KafkaMessageDispatcher(
                courierService,
                deliveryService,
                parcelService,
                objectMapper
        );
    }
}
