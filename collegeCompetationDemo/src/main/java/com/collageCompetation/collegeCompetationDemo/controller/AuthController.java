package com.collageCompetation.collegeCompetationDemo.controller;

import com.collageCompetation.collegeCompetationDemo.config.JwtUtil;
import com.collageCompetation.collegeCompetationDemo.entity.User;
import com.collageCompetation.collegeCompetationDemo.entity.User.Role;
import com.collageCompetation.collegeCompetationDemo.repository.UserRepository;
import com.collageCompetation.collegeCompetationDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; // Ensure this bean is configured in your SecurityConfig

    // -------- Admin Signup --------
    @PostMapping("/user/signup") // This endpoint will now specifically register regular users
    public ResponseEntity<?> userSignup(@RequestBody Map<String, String> signupData) {
        String username = signupData.get("username");
        String password = signupData.get("password");
        String email = signupData.get("email");
        String collegeName = signupData.get("collegeName");

        if (username == null || username.isEmpty() || password == null || password.isEmpty() || email == null || email.isEmpty() || collegeName == null || collegeName.isEmpty()) {
            return ResponseEntity.badRequest().body("All fields (username, password, email, collegeName) are required.");
        }

        if (userRepository.findByUsername(username).isPresent()) {
            return ResponseEntity.badRequest().body("Username already taken!");
        }
        if (userRepository.findByEmail(email).isPresent()) {
            return ResponseEntity.badRequest().body("Email already registered!");
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password)); // Hash the password
        newUser.setEmail(email);
        newUser.setCollegeName(collegeName);
        newUser.setRole(Role.USER); // <--- IMPORTANT: Changed to USER role

        userService.createUser(newUser); // This will save the user

        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/admin/signup")
    public ResponseEntity<?> registerAdmin(@RequestBody User admin) {
        if (userRepository.findByUsername(admin.getUsername()).isPresent() || userRepository.findByEmail(admin.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Username or Email already taken!");
        }
        // Hash the password before saving
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setRole(User.Role.ADMIN); // Set role to ADMIN
        User savedAdmin = userRepository.save(admin);
        return ResponseEntity.ok("Admin registered successfully!");
    }


    // -------- Admin Login --------
    @PostMapping("/admin/login")
    public ResponseEntity<?> adminLogin(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid Admin Credentials");
        }

        User user = userService.findByUsername(username);
        if (user.getRole() != Role.ADMIN) {
            return ResponseEntity.status(403).body("Access Denied: Not an Admin");
        }

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole().name());

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Admin login successful");
        response.put("role", user.getRole());
        response.put("token", token);

        return ResponseEntity.ok(response);
    }

    // -------- User Login --------
    @PostMapping("/user/login")
    public ResponseEntity<?> userLogin(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid User Credentials");
        }

        User user = userService.findByUsername(username);
        if (user.getRole() != Role.USER) {
            return ResponseEntity.status(403).body("Access Denied: Not a User");
        }

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole().name());

        Map<String, Object> response = new HashMap<>();
        response.put("message", "User login successful");
        response.put("role", user.getRole());
        response.put("token", token);

        return ResponseEntity.ok(response);
    }
}
