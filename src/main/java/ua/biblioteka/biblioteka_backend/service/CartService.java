package ua.biblioteka.biblioteka_backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.biblioteka.biblioteka_backend.dao.BookRepository;
import ua.biblioteka.biblioteka_backend.dao.CartItemRepository;
import ua.biblioteka.biblioteka_backend.dao.CartRepository;
import ua.biblioteka.biblioteka_backend.dao.UserRepository;
import ua.biblioteka.biblioteka_backend.dto.CartItemDTO;
import ua.biblioteka.biblioteka_backend.entity.Book;
import ua.biblioteka.biblioteka_backend.entity.Cart;
import ua.biblioteka.biblioteka_backend.entity.CartItem;
import ua.biblioteka.biblioteka_backend.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;


    public Cart getCartByUser(String userId) {

        return cartRepository.findByUser_Id(userId)
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setUser(userRepository.findById(userId).orElseThrow());
                    return cartRepository.save(cart);
                });
    }

    public Cart addItem(String userId, CartItemDTO dto) {
        Cart cart = getCartByUser(userId);
        Book book = bookRepository.findById(dto.getBookId()).orElseThrow();

        // Перевіряємо, чи книга вже є в кошику
        Optional<CartItem> existingItemOpt = cart.getItems().stream()
                .filter(item -> item.getBook().getId().equals(book.getId()))
                .findFirst();

        if (existingItemOpt.isPresent()) {
            CartItem existingItem = existingItemOpt.get();
            existingItem.setQuantity(existingItem.getQuantity() + dto.getQuantity());
            cartItemRepository.save(existingItem);
        } else {
            CartItem newItem = CartItem.builder()
                    .book(book)
                    .quantity(dto.getQuantity())
                    .build();
            cartItemRepository.save(newItem);
            cart.getItems().add(newItem);
        }

        return cartRepository.save(cart);
    }

//    public Cart addItem(String userId, CartItemDTO dto) {
//        Cart cart = getCartByUser(userId);
//        Book book = bookRepository.findById(dto.getBookId()).orElseThrow();
//
//        CartItem item = CartItem.builder()
//                .book(book)
//                .quantity(dto.getQuantity())
//                .build();
//
//        cartItemRepository.save(item);
//        cart.getItems().add(item);
//
//        return cartRepository.save(cart);
//    }

    public void removeItem(String userId, String itemId) {

        Cart cart = getCartByUser(userId);
        CartItem itemToRemove = cart.getItems().stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Item not found in cart"));

        cart.getItems().remove(itemToRemove);
        cartItemRepository.deleteById(itemId);
        cartRepository.save(cart);
//        Cart cart = getCartByUser(userId);
//        cart.getItems().removeIf(item -> item.getId().equals(itemId));
//        cartRepository.save(cart);
//        cartItemRepository.deleteById(itemId);
    }

    public void clearCart(String userId) {
//        Cart cart = getCartByUser(userId);
//        cartItemRepository.deleteAll(cart.getItems());
//        cart.getItems().clear();
//        cartRepository.save(cart);

        Cart cart = getCartByUser(userId);
        List<CartItem> items = new ArrayList<>(cart.getItems()); // створюємо копію
        cart.getItems().clear();
        cartRepository.save(cart);
        cartItemRepository.deleteAll(items);
    }
}
