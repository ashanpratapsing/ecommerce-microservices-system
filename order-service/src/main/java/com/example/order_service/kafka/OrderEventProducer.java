package com.example.order_service.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderEventProducer {

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate; // ✅ FIX

    private static final String TOPIC = "order-created";

    public OrderEventProducer(KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderEvent(OrderEvent event) { // ✅ FIX
        kafkaTemplate.send(TOPIC, event);
        System.out.println("✅ Kafka Event Sent: " + event.getOrderId());
    }
}