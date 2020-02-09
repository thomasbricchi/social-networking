package com.social.socialnetwork.entity;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class SocialUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;


    // dovrebbe essere fetch = FetchType.LAZY, me assumo che non ci siano molti post per quello la lascio Eager come di default
    @OneToMany(mappedBy = "socialUsers", cascade = CascadeType.ALL)
    private Set<Post> posts = new HashSet<>();

    // stessa cosa per i follow
    @OneToMany(mappedBy = "socialUsers", cascade = CascadeType.ALL)
    private Set<Follow> followers = new HashSet<>();

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

    public void addPost(Post post) {
        this.posts.add(post);
    }

    public Set<Follow> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<Follow> followers) {
        this.followers = followers;
    }

    public void addFollower(Follow follow) {
        this.followers.add(follow);
    }

}
