package com.zy;

import com.zy.config.RestConfig;
import com.zy.springcontext.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class RestApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(RestConfig.class);
        app.setBannerMode(Banner.Mode.OFF);

        app.run(args);
    }

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }
}
