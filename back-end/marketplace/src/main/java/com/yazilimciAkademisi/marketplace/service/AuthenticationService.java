package com.yazilimciAkademisi.marketplace.service;

import com.yazilimciAkademisi.marketplace.configuration.JwtService;
import com.yazilimciAkademisi.marketplace.dto.mapper.UserMapper;
import com.yazilimciAkademisi.marketplace.dto.request.AuthenticationRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.request.UserRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.response.AuthenticationResponseDTO;
import com.yazilimciAkademisi.marketplace.entity.User;
import com.yazilimciAkademisi.marketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, UserMapper userMapper, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.userMapper = userMapper;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponseDTO register(UserRequestDTO request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User newAppUser = userMapper.toEntity(request, encodedPassword);
        userRepository.save(newAppUser);
        var jwtToken = jwtService.generateToken(newAppUser);
        AuthenticationResponseDTO authenticationResponseDTO = new AuthenticationResponseDTO();
        authenticationResponseDTO.setToken(jwtToken);
        return authenticationResponseDTO;
    }

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        AuthenticationResponseDTO authenticationResponseDTO = new AuthenticationResponseDTO();
        authenticationResponseDTO.setToken(jwtToken);
        return authenticationResponseDTO;
    }

}
