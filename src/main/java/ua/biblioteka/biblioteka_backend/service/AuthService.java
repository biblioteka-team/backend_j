package ua.biblioteka.biblioteka_backend.service;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import ua.biblioteka.biblioteka_backend.dto.AuthDTO;
import ua.biblioteka.biblioteka_backend.dto.LoginDTO;
import ua.biblioteka.biblioteka_backend.dto.RegisterDTO;
import ua.biblioteka.biblioteka_backend.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.biblioteka.biblioteka_backend.dao.UserRepository;
import ua.biblioteka.biblioteka_backend.enums.Role;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthDTO register(RegisterDTO registerDTO) {
        if (registerDTO.getRole() == Role.ADMIN && userRepository.existsByRole(Role.ADMIN)) {
            throw new RuntimeException("Administrator already exists");
        }

        var user = User.builder()
                .email(registerDTO.getEmail())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .role(registerDTO.getRole())
                .enabled(true)
                .build();

        userRepository.save(user);
        String jwtToken= jwtService.generateToken(user);

        return new AuthDTO(jwtToken);
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
        return new AuthDTO(jwtToken);
    }

}
