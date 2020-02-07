package com.social.socialnetwork.domain;

import com.social.socialnetwork.entity.Post;

import java.time.Clock;

public class PostWithUserAndTime extends PostWithTime {

    private String username;

    public PostWithUserAndTime(Post post, Clock clock) {
        super(post, clock);
        this.username = post.getSocialUsers().getUsername();
    }

    @Override
    public String print() {
        return username + " -> " + super.print();
    }
}
