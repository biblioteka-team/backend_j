package ua.biblioteka.biblioteka_backend.service;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import ua.biblioteka.biblioteka_backend.dto.AuthDTO;
import ua.biblioteka.biblioteka_backend.dto.LoginDTO;
import ua.biblioteka.biblioteka_backend.dto.RegisterDTO;
import ua.biblioteka.biblioteka_backend.dto.UserDTO;
import ua.biblioteka.biblioteka_backend.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.biblioteka.biblioteka_backend.dao.UserRepository;
import ua.biblioteka.biblioteka_backend.enums.Role;
import ua.biblioteka.biblioteka_backend.exception.EmailAlreadyExistsException;
import ua.biblioteka.biblioteka_backend.mapper.UserMapper;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    public AuthDTO register(RegisterDTO registerDTO) {

        Optional<User> existingUser = userRepository.findByEmail(registerDTO.getEmail());
        if (existingUser.isPresent()) {
            throw new EmailAlreadyExistsException(registerDTO.getEmail());
        }

        User user = userMapper.fromRegisterDTO(registerDTO);
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);
        UserDTO userDTO = userMapper.toDO(user);

        return new AuthDTO(jwtToken, userDTO);

    }

    public AuthDTO login(LoginDTO loginDTO){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getEmail(),
                        loginDTO.getPassword()
                )
        );
        User user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("User not founded"));

        String jwtToken = jwtService.generateToken(user);
        UserDTO userDTO = userMapper.toDO(user);
        return new AuthDTO(jwtToken, userDTO);
    }

    public String logout() {

        return "Logout completed. Delete token on client.";
    }

}
