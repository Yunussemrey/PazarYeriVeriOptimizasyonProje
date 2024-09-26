package com.yazilimciAkademisi.marketplace.configuration;

import com.yazilimciAkademisi.marketplace.entity.User;
import com.yazilimciAkademisi.marketplace.entity.enums.Role;
import com.yazilimciAkademisi.marketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SuperAdminInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SuperAdminInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner createSuperAdminUser() {
        return args -> {
            // Check if a super admin user already exists
            if (!userRepository.existsByEmail("superadmin@marketplace.com")) {
                // Create a new super admin user
                User superAdminUser = new User();
                superAdminUser.setFirstName("Super");
                superAdminUser.setLastName("Admin");
                superAdminUser.setEmail("superadmin@marketplace.com");
                superAdminUser.setPassword(passwordEncoder.encode("superadmin123")); // Always encode passwords
                superAdminUser.setRole(Role.SUPER_ADMIN); // Set the super admin role

                // Save the super admin user to the database
                userRepository.save(superAdminUser);
                System.out.println("Super admin created with email: superadmin@marketplace.com and password: superadmin123");
            } else {
                System.out.println("Super admin already exists.");
            }
        };
    }

}
