package ua.biblioteka.biblioteka_backend.service;

import ua.biblioteka.biblioteka_backend.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(String id);
    Optional<User> getUserByEmail(String email);
    List<User> searchUsersByName(String name);
    User updateUser(String id, User user);
    void deleteUser(String id);
}
