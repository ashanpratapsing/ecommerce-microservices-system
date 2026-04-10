package com.example.order_service.kafka;

public class OrderEvent {

    private Long orderId;
    private String userEmail;
    private String product;
    private int quantity;
    private double total;

    public OrderEvent(Long orderId, String userEmail, String product, int quantity, double total) {
        this.orderId = orderId;
        this.userEmail = userEmail;
        this.product = product;
        this.quantity = quantity;
        this.total = total;
    }

    public Long getOrderId() { return orderId; }
    public String getUserEmail() { return userEmail; }
    public String getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public double getTotal() { return total; }
}