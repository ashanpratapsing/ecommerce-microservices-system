package com.example.order_service.controller;

import com.example.order_service.dto.OrderRequestDTO;
import com.example.order_service.dto.OrderResponseDTO;
import com.example.order_service.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(
            @RequestBody @Valid OrderRequestDTO dto,
            Authentication auth) {
        return ResponseEntity.ok(service.createOrder(dto, auth.getName()));
    }

    @GetMapping("/my")
    public ResponseEntity<List<OrderResponseDTO>> myOrders(Authentication auth) {
        return ResponseEntity.ok(service.getMyOrders(auth.getName()));
    }
}
