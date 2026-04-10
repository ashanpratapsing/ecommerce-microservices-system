package com.example.order_service.dto;

import java.time.LocalDateTime;

public class OrderResponseDTO {

    private Long id;
    private String userEmail;
    private String productName;
    private Integer quantity;
    private Double totalPrice;
    private String status;
    private LocalDateTime createdAt;

    public OrderResponseDTO(Long id, String userEmail, String productName,
                            Integer quantity, Double totalPrice,
                            String status, LocalDateTime createdAt) {
        this.id = id;
        this.userEmail = userEmail;
        this.productName = productName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public String getUserEmail() { return userEmail; }
    public String getProductName() { return productName; }
    public Integer getQuantity() { return quantity; }
    public Double getTotalPrice() { return totalPrice; }
    public String getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}