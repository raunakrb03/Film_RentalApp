package com.capgemini.film_rental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

// New imports for filter-based CORS
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.core.Ordered;

import java.util.Arrays;
import java.util.List;

@EnableCaching
@SpringBootApplication
public class FilmRentalApplication {
    private static final Logger logger = LoggerFactory.getLogger(FilmRentalApplication.class);
    public static void main(String[] args) {
        var ctx = SpringApplication.run(FilmRentalApplication.class, args);
        Environment env = ctx.getEnvironment();
        logger.info("Application '{}' started with datasource.url={}", env.getProperty("spring.application.name"), env.getProperty("spring.datasource.url"));
    }

    // Robust CORS filter (works even if Spring Security is present)
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        // Allow the frontend origin; add more origins here if you need them
        config.setAllowedOriginPatterns(List.of("http://localhost:4200"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);
        config.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", config);
        return source;
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterRegistration() {
        CorsFilter filter = new CorsFilter(corsConfigurationSource());
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(filter);
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
