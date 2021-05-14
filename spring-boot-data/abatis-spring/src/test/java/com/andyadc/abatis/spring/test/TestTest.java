package com.andyadc.abatis.spring.test;

import com.alibaba.fastjson.JSON;
import com.andyadc.abatis.spring.test.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestTest {

    @Test
    public void testAbatisSpringByXml() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserMapper userMapper = applicationContext.getBean("userMapper", UserMapper.class);
        User user = userMapper.selectByName("a1");
        System.out.println(JSON.toJSONString(user));
    }
}
