package com.social.socialnetwork.service;

import com.social.socialnetwork.intTest.UtilsTests;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;

/**
 * 2020/02/07
 *
 * @author Sirius
 */
@SpringBootTest
public class TimeFormatterTest extends UtilsTests {

    @Test
    void shouldReturnSeconds() {
        Instant start = Instant.parse("2019-01-03T10:15:30.00Z");
        Instant after = Instant.parse("2019-01-03T10:15:40.00Z");

        final String difference = new TimeFormatter(start, after).getDifference();
        Assertions.assertThat(difference).isEqualTo("( 10 seconds )");
    }

    @Test
    void shouldReturnSeconds59() {
        Instant start = Instant.parse("2019-01-03T10:15:30.00Z");
        Instant after = Instant.parse("2019-01-03T10:16:29.00Z");

        final String difference = new TimeFormatter(start, after).getDifference();
        Assertions.assertThat(difference).isEqualTo("( 59 seconds )");
    }

    @Test
    void shouldReturnOneMinute() {
        Instant start = Instant.parse("2019-01-03T10:15:30.00Z");
        Instant after = Instant.parse("2019-01-03T10:16:31.00Z");

        final String difference = new TimeFormatter(start, after).getDifference();
        Assertions.assertThat(difference).isEqualTo("( 1 minutes )");
    }

    @Test
    void shouldReturn59Minutes() {
        Instant start = Instant.parse("2019-01-03T10:15:30.00Z");
        Instant after = Instant.parse("2019-01-03T11:15:29.00Z");

        final String difference = new TimeFormatter(start, after).getDifference();
        Assertions.assertThat(difference).isEqualTo("( 59 minutes )");
    }

    @Test
    void shouldReturnOneHour() {
        Instant start = Instant.parse("2019-01-03T10:15:30.00Z");
        Instant after = Instant.parse("2019-01-03T11:16:31.00Z");

        final String difference = new TimeFormatter(start, after).getDifference();
        Assertions.assertThat(difference).isEqualTo("( 1 hours )");
    }

    @Test
    void shouldReturn23Hour() {
        Instant start = Instant.parse("2019-01-03T10:15:30.00Z");
        Instant after = Instant.parse("2019-01-04T09:15:33.00Z");

        final String difference = new TimeFormatter(start, after).getDifference();
        Assertions.assertThat(difference).isEqualTo("( 23 hours )");
    }
    @Test
    void shouldReturnDays() {
        Instant start = Instant.parse("2019-01-03T10:15:30.00Z");
        Instant after = Instant.parse("2019-01-06T11:16:31.00Z");

        final String difference = new TimeFormatter(start, after).getDifference();
        Assertions.assertThat(difference).isEqualTo("( 3 days )");
    }


}
