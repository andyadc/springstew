package com.andyadc.abatis.test;

import com.alibaba.fastjson.JSON;
import com.andyadc.abatis.Resources;
import com.andyadc.abatis.SqlSession;
import com.andyadc.abatis.SqlSessionFactory;
import com.andyadc.abatis.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.Reader;

public class TestTest {

    @Test
    public void testAbatisQuery() throws Exception {
        String resource = "abatis-config.xml";
        Reader reader = Resources.getResourceAsReader(resource);

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = sqlSession.selectOne("com.andyadc.abatis.test.UserMapper.selectByName", "a1");

        String userJson = JSON.toJSONString(user);
        System.out.println(userJson);

        sqlSession.close();
        reader.close();
    }
}
