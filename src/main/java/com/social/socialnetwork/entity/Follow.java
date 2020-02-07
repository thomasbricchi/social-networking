package com.social.socialnetwork.entity;

import com.social.socialnetwork.service.ShowData;

import javax.persistence.*;

import java.time.Instant;

@Entity
@Table
public class Follow implements ShowData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private SocialUser socialUsers;

    @ManyToOne
    private SocialUser followedUser;

    public SocialUser getSocialUsers() {
        return socialUsers;
    }

    public void setSocialUsers(SocialUser socialUsers) {
        this.socialUsers = socialUsers;
    }

    public SocialUser getFollowedUser() {
        return followedUser;
    }

    public void setFollowedUser(SocialUser followedUser) {
        this.followedUser = followedUser;
    }

    @Override
    public String print() {
        return getSocialUsers().getUsername() + " follow:" + getFollowedUser().getUsername();
    }

    @Override
    public Instant getData() {
        return Instant.now();
    }
}
