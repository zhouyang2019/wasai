package com.zy.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zy.interceptor.AccessLogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Configuration
@ComponentScan({"com.zy"})
@Import({FlywayAutoConfiguration.class, FlywayConfig.class})
@PropertySource(value = "classpath:application.properties")
@PropertySource(value = "file://${CONFIG_HOME}/application.properties", ignoreResourceNotFound = true)
public class RestConfig implements WebMvcConfigurer {

    @Autowired
    private AccessLogInterceptor logInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor).addPathPatterns("/**");
    }

    @Bean
    public MappingJackson2HttpMessageConverter messageConverter() {
        ObjectMapper om = new ObjectMapper();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        dateFormat.setTimeZone(TimeZone.getDefault());
        om.setDateFormat(dateFormat);
        return new MappingJackson2HttpMessageConverter(om);
    }

}
