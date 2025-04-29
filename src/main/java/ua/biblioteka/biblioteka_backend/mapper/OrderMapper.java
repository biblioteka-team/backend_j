package ua.biblioteka.biblioteka_backend.mapper;

import org.springframework.stereotype.Component;
import ua.biblioteka.biblioteka_backend.dao.BookRepository;
import ua.biblioteka.biblioteka_backend.dto.CartItemDTO;
import ua.biblioteka.biblioteka_backend.dto.OrderDTO;
import ua.biblioteka.biblioteka_backend.entity.Book;
import ua.biblioteka.biblioteka_backend.entity.CartItem;
import ua.biblioteka.biblioteka_backend.entity.Order;
import ua.biblioteka.biblioteka_backend.entity.User;
import ua.biblioteka.biblioteka_backend.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;


@Component
public class OrderMapper {

//    private final BookRepository bookRepository;

    public OrderDTO toDTO(Order order) {
        return OrderDTO.builder()


                .id(order.getId())
                .userId(order.getUser().getId())
                .items(order.getItems())
                .totalAmount(order.getTotalAmount())
                .createdAt(order.getOrderDate())
                .status(order.getStatus())
                .build();
    }


    private CartItemDTO toCartItemDTO(CartItem item) {
        return CartItemDTO.builder()
//                .id(item.getId())
                .bookId(item.getBook().getId())
                .quantity(item.getQuantity())
                .build();
    }

    public Order toEntity(OrderDTO dto, User user, List<CartItem> items) {
        return Order.builder()
                .id(dto.getId())
                .user(user)
                .items(items)
                .totalAmount(dto.getTotalAmount())
                .orderDate(dto.getCreatedAt() != null ? dto.getCreatedAt() : LocalDateTime.now())
                .status(dto.getStatus() != null ? dto.getStatus() : OrderStatus.NEW)
                .build();
    }
}
