package ua.biblioteka.biblioteka_backend.mapper;

import org.springframework.stereotype.Component;
import ua.biblioteka.biblioteka_backend.dto.RegisterDTO;
import ua.biblioteka.biblioteka_backend.dto.UserDTO;
import ua.biblioteka.biblioteka_backend.dto.UserRequestDTO;
import ua.biblioteka.biblioteka_backend.entity.User;

@Component
public class UserMapper {
    public UserDTO toDO (User user){
        if(user == null) return null;

        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());

        return dto;
    }

    public User toEntity (UserRequestDTO dto){

        if (dto == null) return null;

        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        return user;
    }

    public User fromRegisterDTO(RegisterDTO dto) {
        if (dto == null) return null;

        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .build();
    }
}
