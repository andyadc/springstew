package com.andyadc.springboot;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author andaicheng
 * @since 2018/9/16
 */
@Component
public class MyApplicationContextAware implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println(">>> ApplicationContextAware");
    }
}
