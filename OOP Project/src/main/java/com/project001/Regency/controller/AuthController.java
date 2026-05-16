package com.project001.Regency.controller;

import com.project001.Regency.dto.AuthRequest;
import com.project001.Regency.dto.AuthResponse;
import com.project001.Regency.dto.RegisterRequest;
import com.project001.Regency.model.User;
import com.project001.Regency.security.JWTUtil;
import com.project001.Regency.service.UserServiceImpl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class AuthController {

    private final UserServiceImpl userService;
    private final JWTUtil jwtUtil;

    public AuthController(
            UserServiceImpl userService,
            JWTUtil jwtUtil
    ) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    // =========================
    // ADMIN DASHBOARD
    // =========================
    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "Admin Dashboard - Secure";
    }

    // =========================
    // REGISTER USER
    // =========================
    @PostMapping("/register")
    public User register(
            @RequestBody RegisterRequest request
    ) {

        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        user.setRole("USER");

        return userService.register(user);
    }

    // =========================
    // LOGIN USER
    // =========================
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody AuthRequest request
    ) {

        Optional<User> existingUser =
                userService.login(
                        request.getEmail(),
                        request.getPassword()
                );

        // ❌ invalid credentials
        if (existingUser.isEmpty()) {
            return ResponseEntity
                    .status(401)
                    .body("INVALID");
        }

        User loggedUser = existingUser.get();

        // ❌ blocked user
        if (loggedUser.isBlocked()) {
            return ResponseEntity
                    .status(403)
                    .body("BLOCKED");
        }

        // ✅ generate token
        String token = jwtUtil.generateToken(
                loggedUser.getEmail(),
                loggedUser.getRole()
        );

        // ✅ return response DTO
        AuthResponse response = new AuthResponse(
                loggedUser.getId(),
                token,
                loggedUser.getEmail(),
                loggedUser.getRole(),
                loggedUser.getFirstName(),
                loggedUser.getLastName()
        );

        return ResponseEntity.ok(response);
    }

    // =========================
    // GET ALL USERS
    // =========================
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // =========================
    // GET USER BY ID
    // =========================
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // =========================
    // BLOCK USER
    // =========================
    @PutMapping("/{id}/block")
    public User blockUser(@PathVariable Long id) {
        return userService.blockUser(id);
    }

    // =========================
    // UNBLOCK USER
    // =========================
    @PutMapping("/{id}/unblock")
    public User unblockUser(@PathVariable Long id) {
        return userService.unblockUser(id);
    }

    // =========================
    // UPDATE USER
    // =========================
    @PutMapping("/{id}")
    public User updateUser(
            @PathVariable Long id,
            @RequestBody User updatedUser
    ) {
        return userService.updateUser(id, updatedUser);
    }
}