package com.UrbanTamas.demo.controller;

import com.UrbanTamas.demo.data.dto.AuthRequest;
import com.UrbanTamas.demo.data.entity.UserEntity;
import com.UrbanTamas.demo.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request, HttpServletRequest httpRequest) {
        log.info("Register attempt for email: {}", request.getEmail());

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            log.warn("Failed Register attempt for email: {}", request.getEmail());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email in use");
        }

        UserEntity newUser = new UserEntity();
        newUser.setName((request.getName()));
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRole("USER");

        UserEntity savedUser = userRepository.save(newUser);

        // auto login
        createSession(httpRequest, savedUser);

        log.info("User {} registered successfully", request.getEmail());
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request, HttpServletRequest httpRequest) {
        log.info("Logging attempt for email: {}", request.getEmail());

        Optional<UserEntity> userOpt = userRepository.findByEmail(request.getEmail());

        if (userOpt.isPresent() && passwordEncoder.matches(request.getPassword(), userOpt.get().getPassword())) {
            log.info("User {} logged in successfully", request.getEmail());

            createSession(httpRequest, userOpt.get());
            return ResponseEntity.ok(userOpt.get());
        }

        log.warn("Failed login attempt for email: {}", request.getEmail());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        SecurityContextHolder.clearContext();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("USER_ID") != null) {
            Integer userId = (Integer) session.getAttribute("USER_ID");

            return  userRepository.findById(userId)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    private void createSession(HttpServletRequest request, UserEntity user) {
        HttpSession session = request.getSession(true);
        session.setAttribute("USER_ID", user.getId());

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user.getEmail(), null, null);

        SecurityContextHolder.getContext().setAuthentication(auth);
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
    }
}
