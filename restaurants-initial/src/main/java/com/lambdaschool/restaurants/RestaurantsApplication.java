package com.lambdaschool.restaurants;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;

//@EnableJpaAuditing
@SpringBootApplication

public class RestaurantsApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(RestaurantsApplication.class, args);
    }
}
