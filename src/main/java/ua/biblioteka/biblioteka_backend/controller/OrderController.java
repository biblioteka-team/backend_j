package ua.biblioteka.biblioteka_backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.biblioteka.biblioteka_backend.dto.OrderDTO;
import ua.biblioteka.biblioteka_backend.entity.Order;
import ua.biblioteka.biblioteka_backend.enums.OrderStatus;
import ua.biblioteka.biblioteka_backend.mapper.OrderMapper;
import ua.biblioteka.biblioteka_backend.service.OrderService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/orders")
@Tag(name = "Orders")

public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Create order from cart")
    @ApiResponse(responseCode = "200", description = "Order successfully created")
    @PostMapping("/{userId}")
    public ResponseEntity<Order> createOrder(@PathVariable String userId) {
        return ResponseEntity.ok(orderService.createOrder(userId));
    }
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get orders by user")
    @ApiResponse(responseCode = "200", description = "List of orders")
    @GetMapping("/{userId}")
    public ResponseEntity<List<Order>> getUserOrders(@PathVariable String userId) {
        return ResponseEntity.ok(orderService.getOrdersByUser(userId));
    }
    @PreAuthorize("hasRole('USER')")
    @PatchMapping("/{orderId}/status")
    @ApiResponse(responseCode = "200", description = "Status updated")
    @Operation(summary = "Update order status")
    public ResponseEntity<OrderDTO> updateOrderStatus(
            @PathVariable String orderId,
            @RequestParam OrderStatus status) {
        Order updatedOrder = orderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok(orderMapper.toDTO(updatedOrder));
    }
}
