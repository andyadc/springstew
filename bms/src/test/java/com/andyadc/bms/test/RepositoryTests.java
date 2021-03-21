package com.andyadc.bms.test;

import com.andyadc.bms.auth.repository.AuthUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class RepositoryTests {

    @Resource
    private AuthUserRepository authUserRepository;

    @Test
    public void testAuthUser() {
        authUserRepository.findByUsername("twwx62u");
    }
}
