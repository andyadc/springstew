package com.andyadc.security.thymeleaf;

import com.andyadc.security.thymeleaf.entity.User;
import com.andyadc.security.thymeleaf.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UserService {

    private static final Map<Integer, String> ENCODER_TYPE = new HashMap<>();
    private static final Map<String, PasswordEncoder> ENCODER_MAP = new HashMap<>();
    private final static String PASSWORD_FORMAT = "{%s}%s";

    static {
        ENCODER_TYPE.put(0, "noop");
        ENCODER_TYPE.put(1, "bcrypt");
        ENCODER_TYPE.put(2, "pbkdf2");
        ENCODER_TYPE.put(3, "scrypt");
        ENCODER_TYPE.put(4, "sha256");

        ENCODER_MAP.put("noop", NoOpPasswordEncoder.getInstance());
        ENCODER_MAP.put("bcrypt", new BCryptPasswordEncoder());
        ENCODER_MAP.put("pbkdf2", new Pbkdf2PasswordEncoder());
        ENCODER_MAP.put("scrypt", new SCryptPasswordEncoder());
        ENCODER_MAP.put("sha256", new StandardPasswordEncoder());

    }

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user) {
        if (user.getId() == null) {
            user.setCreateTime(new Date());
        }
        user.setUpdateTime(new Date());

        Random random = ThreadLocalRandom.current();
        int idx = random.nextInt(ENCODER_TYPE.size());
        String encoderType = ENCODER_TYPE.get(idx);
        PasswordEncoder passwordEncoder = ENCODER_MAP.get(encoderType);
        String password = String.format(PASSWORD_FORMAT, encoderType, passwordEncoder.encode(user.getPassword()));
        user.setPassword(password);
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
