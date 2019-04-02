package com.andyadc.springboot.configuration;

import com.andyadc.springboot.interceptor.MyInterceptor;
import com.andyadc.springboot.resolver.MyControllerArgsResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author andy.an
 * @since 2019/4/2
 */
@Component
public class MyConfigurerAdapter implements WebMvcConfigurer {

    @Autowired
    private MyInterceptor myInterceptor;

    @Autowired
    private MyControllerArgsResolver controllerArgsResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor).addPathPatterns("/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(controllerArgsResolver);
    }
}
