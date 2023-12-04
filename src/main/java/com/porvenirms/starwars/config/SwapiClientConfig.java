package com.porvenirms.starwars.config;

import com.porvenirms.starwars.client.SwapiClient;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwapiClientConfig {

    @Bean
    public SwapiClient swapiClient() {
        return Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(SwapiClient.class, "https://swapi.dev/api");
    }
}
