package com.andyadc.bms.security.data;

import com.andyadc.bms.entity.User;
import com.andyadc.bms.security.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DatabaseUserService implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseUserService.class);

    //TODO
    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }
}
