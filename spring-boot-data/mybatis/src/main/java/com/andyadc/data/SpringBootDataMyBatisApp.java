package com.andyadc.data;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan({"com.andyadc.data.mapper"})
@SpringBootApplication
public class SpringBootDataMyBatisApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDataMyBatisApp.class, args);
    }
}
