package com.social.socialnetwork.entity;

import com.social.socialnetwork.service.ShowData;

import javax.persistence.*;

import java.time.Instant;


@Entity
@Table
public class Post implements ShowData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String message;
    @ManyToOne
    private SocialUser socialUsers;

    private Instant creationDate;

    public Post() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SocialUser getSocialUsers() {
        return socialUsers;
    }

    public void setSocialUsers(SocialUser socialUsers) {
        this.socialUsers = socialUsers;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String print() {
        return getMessage();
    }

    @Override
    public Instant getData() {
        return Instant.now();
    }
}
