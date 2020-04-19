package com.lexin.step;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author 封心
 */
@SpringBootApplication
@EnableScheduling
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
public class StepApplication {

    public static void main(String[] args) {
        SpringApplication.run(StepApplication.class, args);
    }

}
