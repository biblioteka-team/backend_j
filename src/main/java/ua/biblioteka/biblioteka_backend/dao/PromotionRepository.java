package ua.biblioteka.biblioteka_backend.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.biblioteka.biblioteka_backend.entity.Promotion;

import java.util.Optional;

public interface PromotionRepository extends MongoRepository<Promotion, String> {
    Optional<Promotion> findByBook_Id(String bookId);
}
