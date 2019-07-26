package io.github.srhojo.fenix.ms.warehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.github.srhojo.fenix.microservices.FenixMicroservice;
import io.github.srhojo.utils.restclient.RestClientConfiguration;

@FenixMicroservice
@Import(value = { RestClientConfiguration.class })
public class WarehouseApplication {

    public static void main(final String[] args) {
        SpringApplication.run(WarehouseApplication.class, args);
    }

    @Bean
    public ObjectMapper objectMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        return mapper;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(final CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
                        .allowedOrigins("http://localhost:8080")
                        .allowedHeaders("Authorization", "Cache-Control", "Content-Type", "Accept", "X-Requested-With",
                                "Access-Control-Allow-Origin", "Access-Control-Allow-Headers", "Origin")
                        .exposedHeaders("Access-Control-Expose-Headers", "Authorization", "Cache-Control",
                                "Content-Type", "Access-Control-Allow-Origin", "Access-Control-Allow-Headers",
                                "Origin");

            }
        };
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
