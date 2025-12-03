package com.parking.centers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ConfigCheck implements CommandLineRunner {

    private final Environment env;

    public ConfigCheck(Environment env) {
        this.env = env;
    }

    @Override
    public void run(String... args) {
        System.out.println("=== CONFIGURATION CHECK ===");
        System.out.println("Config Server URL: " +
                env.getProperty("spring.cloud.config.uri", "NOT SET"));

        System.out.println("Database URL: " +
                env.getProperty("spring.datasource.url", "NOT FOUND"));

        System.out.println("Database Driver: " +
                env.getProperty("spring.datasource.driver-class-name", "NOT FOUND"));

        System.out.println("Database Username: " +
                env.getProperty("spring.datasource.username", "NOT FOUND"));

        System.out.println("Example Property: " +
                env.getProperty("example.property", "NOT FOUND"));

        System.out.println("=== END CHECK ===");
    }
}