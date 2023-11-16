package com.kony.shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import static org.junit.jupiter.api.Assertions.*;

@Configuration
@RestartScope
class SpringDataApplicationTest {

    @Bean
    @RestartScope
    @ServiceConnection
    PostgreSQLContainer<?> getPostgres() {
        return new PostgreSQLContainer<>("postgres:16.0-alpine");
    }
    public static void main(String[] args) {

        SpringApplication.from(
                SpringDataApplication::main
                )
                .with(SpringDataApplicationTest.class)
                .run(args);
    }



}