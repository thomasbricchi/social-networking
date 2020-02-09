package com.social.socialnetwork;

import com.social.socialnetwork.service.ActionMaker;
import com.social.socialnetwork.service.ShowData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import java.util.Scanner;

@SpringBootApplication
@Profile("dev")
public class SocialNetworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialNetworkApplication.class, args);
    }

    @Bean
    @Transactional
    public CommandLineRunner bookDemo(ActionMaker actionFactory) {

        return (args) -> {

            String input = "";
            System.out.println("social network starts...");

            while (!input.equals("exit")) {

                Scanner myObj = new Scanner(System.in);  // Create a Scanner object
                input = myObj.nextLine();  // Read user input

                for (ShowData show : actionFactory.doAction(input)) {
                    System.out.println(show.print());
                }

            }

        };
    }

}
