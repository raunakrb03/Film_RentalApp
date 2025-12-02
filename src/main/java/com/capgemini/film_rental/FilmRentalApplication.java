package com.capgemini.film_rental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;



@SpringBootApplication
public class FilmRentalApplication {

    private static final Logger logger = LoggerFactory.getLogger(FilmRentalApplication.class);

    public static void main(String[] args) {
        var ctx = SpringApplication.run(FilmRentalApplication.class, args);
        Environment env = ctx.getEnvironment();
        logger.info("Application '{}' started with datasource.url={}", env.getProperty("spring.application.name"), env.getProperty("spring.datasource.url"));
    }

}
