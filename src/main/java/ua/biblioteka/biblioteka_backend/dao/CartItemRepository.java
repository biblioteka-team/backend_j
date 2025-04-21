package ua.biblioteka.biblioteka_backend.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.biblioteka.biblioteka_backend.entity.CartItem;

@Repository
public interface CartItemRepository extends MongoRepository<CartItem, String> {
}
