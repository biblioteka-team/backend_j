package ua.biblioteka.biblioteka_backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.biblioteka.biblioteka_backend.dao.CartItemRepository;
import ua.biblioteka.biblioteka_backend.dao.CartRepository;
import ua.biblioteka.biblioteka_backend.dao.OrderRepository;
import ua.biblioteka.biblioteka_backend.entity.Cart;
import ua.biblioteka.biblioteka_backend.entity.CartItem;
import ua.biblioteka.biblioteka_backend.entity.Order;
import ua.biblioteka.biblioteka_backend.enums.OrderStatus;
import ua.biblioteka.biblioteka_backend.mapper.OrderMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderMapper orderMapper;

    public Order updateOrderStatus(String orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        return orderRepository.save(order);
    }

    public Order createOrder(String userId) {
        Cart cart = cartRepository.findByUser_Id(userId).orElseThrow();

        List<CartItem> items = cart.getItems();
        if (items == null || items.isEmpty()) {
            throw new RuntimeException("Cannot create order from empty cart");
        }

        BigDecimal total = cart.getItems().stream()
                .map(item -> item.getBook().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = Order.builder()
                .user(cart.getUser())
                .items(cart.getItems())
                .totalAmount(total)
                .orderDate(LocalDateTime.now())
                .status(OrderStatus.NEW)
                .build();

        Order savedOrder = orderRepository.save(order);


        cart.getItems().clear();
        cartRepository.save(cart);

        cartItemRepository.deleteAll(cart.getItems());

        return savedOrder;
    }

    public List<Order> getOrdersByUser(String userId) {
        return orderRepository.findByUser_Id(userId);
    }
}
