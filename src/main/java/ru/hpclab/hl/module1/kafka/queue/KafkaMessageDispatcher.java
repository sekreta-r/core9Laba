package ru.hpclab.hl.module1.kafka.queue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.hpclab.hl.module1.entity.CourierEntity;
import ru.hpclab.hl.module1.entity.DeliveryEntity;
import ru.hpclab.hl.module1.entity.ParcelEntity;
import ru.hpclab.hl.module1.model.EntityType;
import ru.hpclab.hl.module1.model.KafkaOperationMessage;
import ru.hpclab.hl.module1.model.OperationType;
import ru.hpclab.hl.module1.service.CourierService;
import ru.hpclab.hl.module1.service.DeliveryService;
import ru.hpclab.hl.module1.service.ParcelService;

@Component
@RequiredArgsConstructor
public class KafkaMessageDispatcher {

    private final CourierService courierService;
    private final DeliveryService deliveryService;
    private final ParcelService parcelService;
    private final ObjectMapper objectMapper;

    public void dispatch(KafkaOperationMessage msg) {
        EntityType entity = msg.getEntity();
        OperationType operation = msg.getOperation();

        switch (entity) {
            case COURIER -> handleCourier(operation, msg.getPayload());
            case DELIVERY -> handleDelivery(operation, msg.getPayload());
            case PARCEL -> handleParcel(operation, msg.getPayload());
        }
    }

    private void handleCourier(OperationType operation, JsonNode payload) {
        switch (operation) {
            case POST -> courierService.saveCourier(deserialize(payload, CourierEntity.class));
            case PUT -> courierService.updateCourier(payload.get("id").asLong(), deserialize(payload, CourierEntity.class));
            case DELETE -> courierService.deleteCourier(payload.get("id").asLong());
        }
    }

    private void handleDelivery(OperationType operation, JsonNode payload) {
        switch (operation) {
            case POST -> deliveryService.saveDelivery(deserialize(payload, ru.hpclab.hl.module1.dto.DeliveryDTO.class));
            case PUT -> deliveryService.update(payload.get("id").asLong(), deserialize(payload, DeliveryEntity.class));
            case DELETE -> deliveryService.deleteDelivery(payload.get("id").asLong());
        }
    }

    private void handleParcel(OperationType operation, JsonNode payload) {
        switch (operation) {
            case POST -> parcelService.addParcel(deserialize(payload, ParcelEntity.class));
            case PUT -> parcelService.updateParcel(payload.get("id").asLong(), deserialize(payload, ParcelEntity.class));
            case DELETE -> parcelService.deleteParcel(payload.get("id").asLong());
        }
    }

    private <T> T deserialize(JsonNode node, Class<T> clazz) {
        try {
            return objectMapper.treeToValue(node, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to deserialize payload to " + clazz.getSimpleName(), e);
        }
    }
}
