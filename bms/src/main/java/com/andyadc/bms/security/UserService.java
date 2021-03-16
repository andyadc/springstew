package com.andyadc.bms.security;

import com.andyadc.bms.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
}
