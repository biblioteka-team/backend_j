package ua.biblioteka.biblioteka_backend.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import ua.biblioteka.biblioteka_backend.enums.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
@Builder
public class User {
    @Id
    private String id;
    private String name;


    private String password;


    private String email;

    @Field("role")
    @Builder.Default
    private Role role = Role.USER; // "USER", "ADMIN"

    private boolean enabled;
}
