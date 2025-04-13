package ua.biblioteka.biblioteka_backend.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.biblioteka.biblioteka_backend.entity.User;
import ua.biblioteka.biblioteka_backend.enums.Role;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
    boolean existsByRole(Role role);
}
