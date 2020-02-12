package com.example.demo.services;

import com.example.demo.domain.AppUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.example.demo.domain.ApplicationUserRoles.ADMIN;
import static com.example.demo.domain.ApplicationUserRoles.STUDENT;
import static java.util.Optional.ofNullable;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<AppUserDetails> getUserByUsername(final String username) {
        ofNullable(username)
                .orElseThrow(() -> new IllegalArgumentException("Username is not provided"));

        return getAllUsers()
                .stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst();
    }

    private List<AppUserDetails> getAllUsers() {
        return Arrays.asList(
                new AppUserDetails(
                        "annasmith",
                        passwordEncoder.encode("pass123"),
                        true,
                        true,
                        true,
                        true,
                        ADMIN.getGrantedAuthorities()),
                new AppUserDetails(
                        "jamesbond",
                        passwordEncoder.encode("password123"),
                        true,
                        true,
                        true,
                        true,
                        STUDENT.getGrantedAuthorities())
        );
    }
}