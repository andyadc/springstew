package com.andyadc.bms.test;

import com.andyadc.bms.auth.entity.AuthUser;
import com.andyadc.bms.auth.mapper.AuthUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class MapperTests {

    @Resource
    public AuthUserMapper authUserMapper;

    @Test
    public void testAuthUserMapper() {
        AuthUser user = authUserMapper.findByUsername("twwx62u");
        System.out.println(user.getPassword());
    }
}
