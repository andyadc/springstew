package com.andyadc.abatis.spring.boot.test.test;

import com.alibaba.fastjson.JSON;
import com.andyadc.abatis.spring.boot.test.User;
import com.andyadc.abatis.spring.boot.test.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestTest {

    private UserMapper userMapper;

    @Test
    public void testQuery() {
        User user = userMapper.selectByName("a1");
        System.out.println(JSON.toJSONString(user));
    }
}
