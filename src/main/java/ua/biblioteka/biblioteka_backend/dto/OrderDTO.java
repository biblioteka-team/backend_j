package ua.biblioteka.biblioteka_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.biblioteka.biblioteka_backend.entity.CartItem;
import ua.biblioteka.biblioteka_backend.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    private String id;
    private String userId;
    private List<CartItem> items;
    private BigDecimal totalAmount;
    private LocalDateTime createdAt;
    private OrderStatus status;
}
