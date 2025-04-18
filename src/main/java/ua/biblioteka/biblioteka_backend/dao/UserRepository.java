package ua.biblioteka.biblioteka_backend.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.biblioteka.biblioteka_backend.entity.User;
import ua.biblioteka.biblioteka_backend.enums.Role;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
    List<User> findByNameContainingIgnoreCase(String name);
    boolean existsByRole(Role role);
}
