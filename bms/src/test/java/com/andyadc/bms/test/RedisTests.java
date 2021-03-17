package com.andyadc.bms.test;

import com.andyadc.bms.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
public class RedisTests {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testRedisTemplate() {
        redisTemplate.opsForValue().set("u1", new User());
        System.out.println(redisTemplate.opsForValue().get("u1"));
    }
}
