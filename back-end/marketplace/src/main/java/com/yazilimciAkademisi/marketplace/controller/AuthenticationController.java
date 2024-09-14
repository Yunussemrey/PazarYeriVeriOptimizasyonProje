package com.yazilimciAkademisi.marketplace.controller;

import com.yazilimciAkademisi.marketplace.dto.request.AuthenticationRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.request.UserRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.response.AuthenticationResponseDTO;
import com.yazilimciAkademisi.marketplace.service.AuthenticationService;
import com.yazilimciAkademisi.marketplace.service.UserService;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.util.Base64;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authService;

    @Autowired
    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDTO> register(@RequestBody UserRequestDTO request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(@RequestBody AuthenticationRequestDTO request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

}
