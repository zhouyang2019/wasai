package com.zy.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Slf4j
@Configuration
public class FlywayConfig {

    @Value("${wasai.flyway.auto.migrate}")
    private Boolean autoMigrate;

    @Bean
    @DependsOn("dataSource")
    public FlywayMigrationStrategy flywayMigrationStrategy() {
        return flyway -> {
            if (autoMigrate) {
                log.info("auto migrate start");
                try {
                    flyway.migrate();
                    log.info("auto migrate done");
                } catch (Throwable ex) {
                    log.error("migrate error,retry one time after repair", ex);
                    flyway.repair();
                    flyway.migrate();
                }
            } else {
                log.info("auto migrate disable");
            }
        };
    }

}
