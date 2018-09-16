package com.andyadc.springboot;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author andaicheng
 * @since 2018/9/16
 */
@Component
public class MyInitializingBean implements InitializingBean {

    @Override
    public void afterPropertiesSet() {
        System.out.println(">>> InitializingBean");
    }
}
