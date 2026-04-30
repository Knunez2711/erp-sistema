package com.erp.application.usecase;

import com.erp.domain.exception.InvalidCredentialsException;
import com.erp.domain.exception.UserNotFoundException;
import com.erp.domain.model.User;
import com.erp.infrastructure.persistence.UserEntity;
import com.erp.infrastructure.repository.UserRepository;
import com.erp.infrastructure.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String register(String email, String password) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("El email ya está registrado: " + email);
        }

        UserEntity entity = new UserEntity();
        entity.setEmail(email);
        entity.setPassword(passwordEncoder.encode(password));
        entity.setRole("SELLER");

        userRepository.save(entity);

        User user = entity.toDomain();
        return jwtService.generateToken(user.getEmail());
    }

    public String login(String email, String password) {
        UserEntity entity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));

        if (!passwordEncoder.matches(password, entity.getPassword())) {
            throw new InvalidCredentialsException();
        }

        User user = entity.toDomain();
        return jwtService.generateToken(user.getEmail());
    }
}
