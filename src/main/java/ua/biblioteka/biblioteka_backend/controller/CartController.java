package ua.biblioteka.biblioteka_backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.biblioteka.biblioteka_backend.dto.CartItemDTO;
import ua.biblioteka.biblioteka_backend.entity.Cart;
import ua.biblioteka.biblioteka_backend.service.CartService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/cart")
@Tag(name = "Cart")

public class CartController {
    private final CartService cartService;

    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get user's cart")
    @ApiResponse(responseCode = "200", description = "Cart found")
    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable String userId) {
        return ResponseEntity.ok(cartService.getCartByUser(userId));
    }
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Add item to cart")
    @ApiResponse(responseCode = "200", description = "Book added to your basket")
    @PostMapping("/{userId}/items")
    public ResponseEntity<Cart> addItem(@PathVariable String userId,
                                        @RequestBody @Valid CartItemDTO dto) {
        return ResponseEntity.ok(cartService.addItem(userId, dto));
    }
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Remove item from cart")
    @ApiResponse(responseCode = "200", description = "Book removed from the basket")
    @DeleteMapping("/{userId}/items/{itemId}")
    public ResponseEntity<Void> removeItem(@PathVariable String userId,
                                           @PathVariable String itemId) {
        cartService.removeItem(userId, itemId);
        return ResponseEntity.noContent().build();
    }
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Clear cart")
    @ApiResponse(responseCode = "200", description = "Cart emptied")
    @DeleteMapping("/{userId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable String userId) {
        cartService.clearCart(userId);
        return ResponseEntity.noContent().build();
    }
}
