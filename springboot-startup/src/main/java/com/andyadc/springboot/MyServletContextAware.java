package com.andyadc.springboot;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * @author andaicheng
 * @since 2018/9/16
 */
@Component
public class MyServletContextAware implements ServletContextAware {

    @Override
    public void setServletContext(ServletContext servletContext) {
        System.out.println(">>> ServletContextAware");
    }
}
