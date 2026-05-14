package com.GenAi.controller;

import com.GenAi.exception.UserNotFoundException;
import com.GenAi.model.User;
import com.GenAi.model.dto.AuthRequest;
import com.GenAi.model.dto.AuthResponse;
import com.GenAi.repo.UserRepo;
import com.GenAi.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    UserRepo userRepo;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        // Get the authenticated principal
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Fetch the full User entity from DB
        User user = userRepo.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // Use the DB role, not the authority from UserDetails
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());

        return ResponseEntity.ok(new AuthResponse(token));
    }

}


