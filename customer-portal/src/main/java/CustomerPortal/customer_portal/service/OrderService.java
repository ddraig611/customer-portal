package com.example.portal.service;

import com.example.portal.entity.Order;
import com.example.portal.entity.User;
import com.example.portal.repository.OrderRepository;
import com.example.portal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public void createOrder(Order order, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        order.setUser(user);
        order.setStatus("CREATED");
        order.setCreatedDate(LocalDateTime.now());
        orderRepository.save(order);
    }

    public List<Order> getOrdersByUser(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return orderRepository.findByUser(user);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }

    public void updateOrder(Order order) {
        orderRepository.save(order);
    }
}
