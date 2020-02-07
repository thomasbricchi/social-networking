package com.social.socialnetwork.service;

import java.time.Duration;
import java.time.Instant;

/**
 * 2020/02/07
 *
 * @author Sirius
 */
public class TimeFormatter {

    private Instant startDate;
    private Instant endDate;

    public TimeFormatter(Instant startDate, Instant endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * get the difference in seconds,minutes,hours,days based on time
     *
     * @return difference based on time
     */
    public String getDifference() {
        Duration difference = Duration.between(startDate, endDate);
        return difference.getSeconds() < 60 ? "( " + difference.getSeconds() + " seconds ago )" :
            difference.toMinutes() < 60 ? "( " + difference.toMinutes() + " minutes ago )" :
                difference.toHours() < 24 ? "( " + difference.toHours() + " hours ago )" : "( " + difference.toDays() + " days ago )";

    }
}
