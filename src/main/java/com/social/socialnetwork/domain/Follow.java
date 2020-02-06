package com.social.socialnetwork.domain;

import javax.persistence.*;

/**
 * 2020/02/06
 *
 * @author Sirius
 */
@Entity
@Table
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long followed;

    private Long social_user_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFollowed() {
        return followed;
    }

    public void setFollowed(Long followed) {
        this.followed = followed;
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
