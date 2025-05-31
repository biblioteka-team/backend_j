package ua.biblioteka.biblioteka_backend.dto;

import lombok.Data;

@Data
public class UserRequestDTO {
    private String id;
    private String name;
    private String email;
}
