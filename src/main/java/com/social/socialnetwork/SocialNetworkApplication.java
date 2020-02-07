package com.social.socialnetwork;

import com.social.socialnetwork.service.ActionFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SocialNetworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialNetworkApplication.class, args);
    }

    @Bean
    public CommandLineRunner bookDemo(ActionFactory actionFactory) {
        return (args) -> {

            actionFactory.print();
        };
    }

}
