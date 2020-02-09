package com.social.socialnetwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// ho fatto questa classe per i test, perchè se no rimanevano bloccati per l'inserimento di Scanner
public class SocialNetworkApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(SocialNetworkApplicationTest.class, args);
    }


}
