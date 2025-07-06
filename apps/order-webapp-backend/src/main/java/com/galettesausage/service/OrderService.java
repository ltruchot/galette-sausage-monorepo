package com.galettesausage.service;

import com.galettesausage.dto.OrderDto;
import com.galettesausage.entity.BasketItem;
import com.galettesausage.entity.Order;
import com.galettesausage.repository.BasketItemRepository;
import com.galettesausage.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BasketItemRepository basketItemRepository;

    public Order createOrder(String sessionId, OrderDto orderDto) {
        // Get basket items
        List<BasketItem> basketItems = basketItemRepository.findBySessionId(sessionId);
        if (basketItems.isEmpty()) {
            throw new RuntimeException("Basket is empty");
        }

        // Calculate total price
        BigDecimal totalPrice = basketItems.stream()
                .map(BasketItem::calculatePrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Create order
        Order order = new Order();
        order.setSessionId(sessionId);
        order.setCustomerName(orderDto.getName());
        order.setPhone(orderDto.getPhone());
        order.setAddress(orderDto.getAddress());
        order.setNotes(orderDto.getNotes());
        order.setItems(basketItems);
        order.setTotalPrice(totalPrice);
        
        Order savedOrder = orderRepository.save(order);

        // Clear basket after order
        basketItemRepository.deleteBySessionId(sessionId);

        return savedOrder;
    }

    public List<Order> getOrdersBySession(String sessionId) {
        return orderRepository.findBySessionIdOrderByCreatedAtDesc(sessionId);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    public List<Order> getOrdersByStatus(Order.OrderStatus status) {
        return orderRepository.findByStatus(status);
    }

    public Order updateOrderStatus(Long id, Order.OrderStatus status) {
        Order order = getOrderById(id);
        order.setStatus(status);
        return orderRepository.save(order);
    }
}