package com.social.socialnetwork.domain;

import com.social.socialnetwork.entity.Post;
import com.social.socialnetwork.service.ShowData;
import com.social.socialnetwork.service.TimeFormatter;

import java.time.Clock;
import java.time.Instant;

public class PostWithTime implements ShowData {
    private Clock clock;
    private String message;
    private Instant creationDate;

    public PostWithTime(Post post, Clock clock) {
        this.message = post.getMessage();
        this.creationDate = post.getCreationDate();
        this.clock = clock;
    }

    @Override
    public String print() {
        return message + " " + new TimeFormatter(creationDate, Instant.now(clock)).getDifference();
    }

    @Override
    public Instant getData() {
        return creationDate;
    }
}
