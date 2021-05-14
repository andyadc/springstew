package com.andyadc.abatis.spring.boot.autoconfigure;

import com.andyadc.abatis.SqlSessionFactory;
import com.andyadc.abatis.spring.MapperFactoryBean;
import com.andyadc.abatis.spring.MapperScannerConfigurer;
import com.andyadc.abatis.spring.boot.SqlSessionFactorySpringBootBuilder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
@ConditionalOnClass(value = {SqlSessionFactory.class})
@EnableConfigurationProperties(value = {AbatisProperties.class})
public class AbatisAutoConfiguration implements InitializingBean {

    @Bean
    @ConditionalOnMissingBean
    public SqlSessionFactory sqlSessionFactory(Connection connection, AbatisProperties properties) throws Exception {
        return new SqlSessionFactorySpringBootBuilder().build(connection, properties.getMapperLocations());
    }

    @Bean
    @ConditionalOnMissingBean
    public Connection connection(AbatisProperties properties) {
        try {
            Class.forName(properties.getDriver());
            return DriverManager.getConnection(properties.getUrl(), properties.getUsername(), properties.getPassword());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    static class AutoConfiguredMapperScannerRegistrar implements EnvironmentAware, ImportBeanDefinitionRegistrar {

        private String basePackage;

        @Override
        public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(MapperScannerConfigurer.class);
            builder.addPropertyValue("basePackage", basePackage);
            registry.registerBeanDefinition(MapperScannerConfigurer.class.getName(), builder.getBeanDefinition());
        }

        @Override
        public void setEnvironment(Environment environment) {
            this.basePackage = environment.getProperty("abatis.config.base-mapper-package");
        }
    }

    @Configuration
    @Import(AutoConfiguredMapperScannerRegistrar.class)
    @ConditionalOnMissingBean({MapperFactoryBean.class, MapperScannerConfigurer.class})
    static class MapperScannerRegistrarNotFoundConfiguration implements InitializingBean {

        @Override
        public void afterPropertiesSet() throws Exception {

        }
    }
}
