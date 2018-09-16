package com.andyadc.springboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author andaicheng
 * @since 2018/9/16
 */
@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) {
        System.out.println(">>> CommandLineRunner");
    }
}
