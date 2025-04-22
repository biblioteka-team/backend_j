package ua.biblioteka.biblioteka_backend.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.biblioteka.biblioteka_backend.entity.Order;
import ua.biblioteka.biblioteka_backend.entity.User;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByUser_Id(String userId);
}
