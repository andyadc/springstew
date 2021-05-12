package com.andyadc.abatis.test;

import com.andyadc.abatis.Resources;
import com.andyadc.abatis.SqlSessionFactory;
import com.andyadc.abatis.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.Reader;

public class DataTest {

    @Test
    public void testXMLReader() {
        String resource = "abatis-config-datasource.xml";
        Reader reader = Resources.getResourceAsReader(resource);

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }
}
