package ru.hpclab.hl.module1.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KafkaOperationMessage {
    private EntityType entity;
    private OperationType operation;
    private JsonNode payload;
}
