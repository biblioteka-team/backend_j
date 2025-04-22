package ua.biblioteka.biblioteka_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.biblioteka.biblioteka_backend.enums.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
    private String email;
    private String password;
    private String name;

//    private Role role = Role.USER;
}
