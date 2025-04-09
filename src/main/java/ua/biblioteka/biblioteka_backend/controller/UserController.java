package ua.biblioteka.biblioteka_backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.biblioteka.biblioteka_backend.dao.UserRepository;
import ua.biblioteka.biblioteka_backend.dto.UserDto;
import ua.biblioteka.biblioteka_backend.entity.User;

import java.util.List;
import java.util.Optional;

@Tag(name = "User")
@RestController
@Data
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers (){
        return ResponseEntity.ok(this.userRepository.findAll());
    }

    @Operation(
            summary = "Create and add new user"
    )
    @PostMapping("/user")
    public ResponseEntity<User> createUser (@RequestBody UserDto userDto){

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRoles(userDto.getRoles());

        return ResponseEntity.status(201).body(this.userRepository.save(user));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity getUserById(@PathVariable String id){

        Optional<User> user = this.userRepository.findById(id);
        if(user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.ok("The user with id: " + id + " was not found.");
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity deleteUserById(@PathVariable String id) {

        Optional<User> user = this.userRepository.findById(id);

        if(user.isPresent()) {
            this.userRepository.deleteById(id);
            return ResponseEntity.ok("Success.");
        } else {
            return ResponseEntity.ok("The user with id: " + id + " was not found.");
        }
    }
}
