package ua.biblioteka.biblioteka_backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.biblioteka.biblioteka_backend.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    private String id;

    @DBRef
    private User user;

    @DBRef
    private List<CartItem> items;

    private BigDecimal totalAmount;

    private LocalDateTime orderDate;


    private OrderStatus status;
}
