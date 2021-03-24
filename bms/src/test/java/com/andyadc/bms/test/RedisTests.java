package com.andyadc.bms.test;

import com.andyadc.bms.cache.redis.RedisOperator;
import com.andyadc.bms.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SpringBootTest
public class RedisTests {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private RedisOperator redisOperator;

    @Test
    public void testExist() {
        System.out.println(redisOperator.exist("a"));
    }

    @Test
    public void testScanKeys() {
        Set<String> keys = redisOperator.keys("a*", 100L);
        System.out.println(keys);
    }

    @Test
    public void testRedisTemplate() {
        redisTemplate.opsForValue().set("u1", new User());
        System.out.println(redisTemplate.opsForValue().get("u1"));
    }

    @Test
    public void testRedisOperator() {
//        redisOperator.set("u2", new User());
//        redisOperator.set("u3", new User());
//        System.out.println(redisOperator.get("u1"));

        List<String> keys = Arrays.asList("a1", "a2", "a3");
        List<Object> values = redisOperator.multiGet(keys);
        System.out.println(values);
    }

    @Test
    public void testPipeline() {
        Map<String, Object> map = new HashMap<>();
        map.put("a1", new User(1L, "a1", "aaaaaa"));
        map.put("a2", new User(2L, "a2", "bbbbbb"));
        map.put("a3", new User(3L, "a3", "vvvvvv"));
        redisOperator.batctSet(map);
    }
}
