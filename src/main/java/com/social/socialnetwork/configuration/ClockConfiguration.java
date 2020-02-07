package com.social.socialnetwork.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

/**
 * Clock configuration for test purpose
 * <p>
 * 2019/08/26
 *
 * @author Sirius
 */
@Configuration
public class ClockConfiguration {

    /**
     * Creates application clock
     *
     * @return The clock
     */
    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
