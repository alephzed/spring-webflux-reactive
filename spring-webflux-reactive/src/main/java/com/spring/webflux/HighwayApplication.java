package com.spring.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
@EnableScheduling
public class HighwayApplication 
{
    public static void main( String[] args )
    {
        SpringApplication.run(HighwayApplication.class, args);
    }
}
