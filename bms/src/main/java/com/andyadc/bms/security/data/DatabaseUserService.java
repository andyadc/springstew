package com.andyadc.bms.security.data;

import com.andyadc.bms.entity.User;
import com.andyadc.bms.security.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DatabaseUserService implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseUserService.class);

    static Map<String, User> userMap = new HashMap<>(10);

    static {
        User user = new User();
        user.setUsername("adc");
        user.setPassword("123");

        List<String> authorities = new ArrayList<>();
        authorities.add("user:add");
        authorities.add("user:del");
        authorities.add("user:update");
        user.setAuthorities(authorities);

        userMap.put("adc", user);

        user = new User();
        user.setUsername("admin");
        user.setPassword("123");

        authorities = new ArrayList<>();
        authorities.add("user:add");
        user.setAuthorities(authorities);

        userMap.put("admin", user);
    }

    //TODO
    @Override
    public Optional<User> findByUsername(String username) {
        User user = userMap.get(username);

        return Optional.of(user);
    }
}
