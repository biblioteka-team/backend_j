package ua.biblioteka.biblioteka_backend.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private String email;
    private String password;
}
