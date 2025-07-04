package com.galettesausage.controller;

import com.galettesausage.dto.OrderDto;
import com.galettesausage.entity.Order;
import com.galettesausage.service.OrderService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin
public class OrderController {
    
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(
            @Valid @RequestBody OrderDto orderDto,
            HttpSession session) {
        String sessionId = session.getId();
        Order order = orderService.createOrder(sessionId, orderDto);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/my-orders")
    public ResponseEntity<List<Order>> getMyOrders(HttpSession session) {
        String sessionId = session.getId();
        List<Order> orders = orderService.getOrdersBySession(sessionId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Order>> getOrdersByStatus(@PathVariable Order.OrderStatus status) {
        List<Order> orders = orderService.getOrdersByStatus(status);
        return ResponseEntity.ok(orders);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable Long id,
            @RequestParam Order.OrderStatus status) {
        Order order = orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok(order);
    }
}