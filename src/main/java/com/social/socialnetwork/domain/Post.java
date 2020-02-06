package com.social.socialnetwork.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * 2020/02/06
 *
 * @author Sirius
 */
@Entity
@Table
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long social_user_id;

    private String message;

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

    @ManyToOne
    private SocialUser socialUsers;

    public SocialUser getSocialUsers() {
        return socialUsers;
    }

    public void setSocialUsers(SocialUser socialUsers) {
        this.socialUsers = socialUsers;
    }
}
