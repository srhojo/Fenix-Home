package io.github.srhojo.fenix.ms.users;

import org.springframework.boot.SpringApplication;

import io.github.srhojo.fenix.microservices.FenixMicroservice;
import io.github.srhojo.utils.restclient.EnableRestClient;

@FenixMicroservice
@EnableRestClient
public class UsersApplication {

    public static void main(final String[] args) {
        SpringApplication.run(UsersApplication.class, args);
    }

}
