package com.social.socialnetwork.domain;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * 2020/02/06
 *
 * @author Sirius
 */
@Entity
@Table
public class SocialUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    @OneToMany(mappedBy = "socialUsers")
    private Set<Post> posts = new HashSet<>();

    @OneToMany(mappedBy = "socialUsers")
    private Set<Follow> followers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<Follow> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<Follow> followers) {
        this.followers = followers;
    }
}
