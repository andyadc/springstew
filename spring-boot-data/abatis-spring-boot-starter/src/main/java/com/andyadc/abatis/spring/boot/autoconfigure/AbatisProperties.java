package com.andyadc.abatis.spring.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = AbatisProperties.ABATIS_PREFIX)
public class AbatisProperties {

    public static final String ABATIS_PREFIX = "abatis.config";

    private String driver;              // com.mysql.jdbc.Driver
    private String url;                 // jdbc:mysql://127.0.0.1:3306/bugstack?useUnicode=true
    private String username;            // root
    private String password;            // 123456
    private String mapperLocations;     // classpath*:mapper/*.xml
    private String baseMapperPackage;   // com.andyadc.**.mapper

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMapperLocations() {
        return mapperLocations;
    }

    public void setMapperLocations(String mapperLocations) {
        this.mapperLocations = mapperLocations;
    }

    public String getBaseMapperPackage() {
        return baseMapperPackage;
    }

    public void setBaseMapperPackage(String baseMapperPackage) {
        this.baseMapperPackage = baseMapperPackage;
    }
}
