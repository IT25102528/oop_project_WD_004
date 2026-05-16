package com.project001.Regency.service;

import com.project001.Regency.model.User;
import com.project001.Regency.repository.UsersRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UsersRepo userRepository;

    public UserServiceImpl(UsersRepo userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // =========================
    // REGISTER USER
    // =========================
    public User register(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists!");
        }

        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER");
        }

        user.setBlocked(false); // default

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);

    }

    // =========================
    // LOGIN USER
    // =========================
    public Optional<User> login(String email, String password) {

        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return user;
        }


        return Optional.empty();
    }

    // =========================
    // GET ALL USERS
    // =========================
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // =========================
    // GET USER BY ID
    // =========================
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // =========================
    // BLOCK USER
    // =========================
    public User blockUser(Long id) {
        User user = getUserById(id);
        user.setBlocked(true);
        return userRepository.save(user);
    }

    // =========================
    // UNBLOCK USER
    // =========================
    public User unblockUser(Long id) {
        User user = getUserById(id);
        user.setBlocked(false);
        return userRepository.save(user);
    }

    // =========================
// UPDATE USER
// =========================
    public User updateUser(Long id, User updatedUser) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        // UPDATE ONLY PROFILE DATA
        user.setFirstName(updatedUser.getFirstName());

        user.setLastName(updatedUser.getLastName());

        user.setEmail(updatedUser.getEmail());

        // KEEP OLD ROLE
        user.setRole(user.getRole());

        // KEEP BLOCK STATUS
        user.setBlocked(user.isBlocked());

        // KEEP PASSWORD
        user.setPassword(user.getPassword());

        return userRepository.save(user);
    }
}