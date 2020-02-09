package com.social.socialnetwork.intTest;

import com.social.socialnetwork.entity.Post;
import com.social.socialnetwork.service.ActionMaker;
import com.social.socialnetwork.service.ShowData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Set;


class PostingIntTest extends UtilsTests {


    @Autowired
    private ActionMaker target;


    @Test
    @Transactional
        // for read posts of user
    void shoulPostForANewUser() {

        // THEN
        Instant instant = Instant.parse("2019-01-03T10:15:30.00Z");
        setupClockMock(instant);
        Assertions.assertThat(socialUserRepository.count()).isEqualTo(0);
        Assertions.assertThat(postRepository.count()).isEqualTo(0);

        // WHEN
        final List<ShowData> showData = target.doAction("username -> messaggio");


        // THEN
        Assertions.assertThat(showData).hasSize(0);
        Assertions.assertThat(socialUserRepository.count()).isEqualTo(1);
        Assertions.assertThat(postRepository.count()).isEqualTo(1);

        // from social user
        final Set<Post> userPosts = socialUserRepository.findByUsernameIgnoreCase("username").get().getPosts();
        Assertions.assertThat(userPosts).hasSize(1);

        final Post post = userPosts.stream().findFirst().get();
        Assertions.assertThat(post.getMessage()).isEqualTo("messaggio");
        Assertions.assertThat(post.getCreationDate()).isEqualTo(Instant.parse("2019-01-03T10:15:30.00Z"));

        // from side post
        Assertions.assertThat(postRepository.findAll().get(0).getSocialUsers().getUsername()).isEqualTo("username");
        Assertions.assertThat(postRepository.findAll().get(0).getMessage()).isEqualTo("messaggio");
    }

    @Test
    @Transactional
        // for read posts of user
    void shoulPostForAnExistingUser() {

        // GIVEN
        createSocialUser("username");
        Assertions.assertThat(socialUserRepository.count()).isEqualTo(1);
        Assertions.assertThat(postRepository.count()).isEqualTo(0);


        // WHEN
        final List<ShowData> showData = target.doAction("username -> messaggio");

        // THEN
        Assertions.assertThat(showData).hasSize(0);
        Assertions.assertThat(socialUserRepository.count()).isEqualTo(1);
        Assertions.assertThat(postRepository.count()).isEqualTo(1);

        final Set<Post> userPosts = socialUserRepository.findByUsernameIgnoreCase("username").get().getPosts();
        Assertions.assertThat(userPosts).hasSize(1);
        Assertions.assertThat(userPosts.stream().findFirst().get().getMessage()).isEqualTo("messaggio");
    }

    @Test
    @Transactional
        // for read posts of user
    void shoulPostForAnExistingUserWithAlreadyOnePost() {

        // GIVEN
        createSocialUserWithPost("username", "messaggio 1");
        Assertions.assertThat(socialUserRepository.count()).isEqualTo(1);
        Assertions.assertThat(postRepository.count()).isEqualTo(1);


        // WHEN
        final List<ShowData> showData = target.doAction("username -> messaggio 2");

        // THEN
        Assertions.assertThat(showData).hasSize(0);
        Assertions.assertThat(socialUserRepository.count()).isEqualTo(1);
        Assertions.assertThat(postRepository.count()).isEqualTo(2);

        final Set<Post> userPosts = socialUserRepository.findByUsernameIgnoreCase("username").get().getPosts();
        Assertions.assertThat(userPosts).hasSize(2);
    }


}
