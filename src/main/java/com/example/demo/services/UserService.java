package com.example.demo.services;

import com.example.demo.domain.AppUserDetails;

import java.util.Optional;

public interface UserService {

    Optional<AppUserDetails> getUserByUsername(final String username);
}
