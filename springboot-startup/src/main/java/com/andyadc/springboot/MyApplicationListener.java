package com.andyadc.springboot;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author andaicheng
 * @since 2018/9/16
 */
@Component
public class MyApplicationListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.out.println(">>> ApplicationListener");
    }
}
