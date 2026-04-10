package com.example.order_service.service;

import com.example.order_service.dto.OrderRequestDTO;
import com.example.order_service.dto.OrderResponseDTO;
import com.example.order_service.entity.Order;
import com.example.order_service.kafka.OrderEventProducer;
import com.example.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;
import com.example.order_service.kafka.OrderEvent;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository repo;
    private final OrderEventProducer kafkaProducer;

    public OrderService(OrderRepository repo, OrderEventProducer kafkaProducer) {
        this.repo = repo;
        this.kafkaProducer = kafkaProducer;
    }

    public OrderResponseDTO createOrder(OrderRequestDTO dto, String userEmail) {

        Order order = new Order();
        order.setUserEmail(userEmail);
        order.setProductName(dto.getProductName());
        order.setQuantity(dto.getQuantity());
        order.setTotalPrice(dto.getTotalPrice());

        Order saved = repo.save(order);

        // 🔥 FIX START (Object instead of String)
        OrderEvent event = new OrderEvent(
                saved.getId(),
                saved.getUserEmail(),
                saved.getProductName(),
                saved.getQuantity(),
                saved.getTotalPrice()
        );

        kafkaProducer.sendOrderEvent(event);
       

        return toDTO(saved);
    }

    public List<OrderResponseDTO> getMyOrders(String userEmail) {
        return repo.findByUserEmail(userEmail)
                .stream().map(this::toDTO)
                .collect(Collectors.toList());
    }

    private OrderResponseDTO toDTO(Order o) {
        return new OrderResponseDTO(
                o.getId(), o.getUserEmail(), o.getProductName(),
                o.getQuantity(), o.getTotalPrice(),
                o.getStatus(), o.getCreatedAt()
        );
    }
}