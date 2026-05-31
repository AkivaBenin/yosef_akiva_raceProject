package com.innovativelearning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

@Configuration
@Profile("prod")
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        String host = System.getenv("DB_HOST");
        String port = System.getenv("DB_PORT");
        String name = System.getenv("DB_NAME");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        // Safety logs to print directly to your Render dashboard screen
        System.out.println("=== COUPLING CHECKLIST ===");
        System.out.println("DB_HOST found: " + (host != null));
        System.out.println("DB_PORT found: " + (port != null));
        System.out.println("DB_NAME found: " + (name != null));
        System.out.println("DB_USER found: " + (user != null));
        System.out.println("==========================");

        dataSource.setUrl("jdbc:mysql://" + host + ":" + port + "/" + name + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC");
        dataSource.setUsername(user);
        dataSource.setPassword(password);

        return dataSource;
    }
}