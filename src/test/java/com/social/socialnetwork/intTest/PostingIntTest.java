package com.social.socialnetwork.intTest;

import com.social.socialnetwork.entity.Post;
import com.social.socialnetwork.service.ActionFactory;
import com.social.socialnetwork.service.ShowData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


class PostingIntTest extends UtilsTests {


    @Autowired
    private ActionFactory target;


    @Test
    @Transactional
        // for read posts of user
    void shoulPostForANewUser() {

        Instant instant = Instant.parse("2019-01-03T10:15:30.00Z");
        setupClockMock(instant);

        Assertions.assertThat(socialUserRepository.count()).isEqualTo(0);
        Assertions.assertThat(postRepository.count()).isEqualTo(0);

        final List<ShowData> showData = target.doAction("username -> messaggio");


        Assertions.assertThat(showData).hasSize(0);
        Assertions.assertThat(socialUserRepository.count()).isEqualTo(1);
        Assertions.assertThat(postRepository.count()).isEqualTo(1);

        // from social user
        final Set<Post> posts = socialUserRepository.findByUsernameIgnoreCase("username").get().getPosts();
        Assertions.assertThat(posts).hasSize(1);
        final ArrayList<Post> postsArray = new ArrayList<>(posts);
        final Post post = postsArray.get(0);
        Assertions.assertThat(post.getMessage()).isEqualTo("messaggio");
        Assertions.assertThat(post.getCreationDate()).isEqualTo(Instant.parse("2019-01-03T10:15:30.00Z"));

        // from post
        Assertions.assertThat(postRepository.findAll().get(0).getSocialUsers().getUsername()).isEqualTo("username");
        Assertions.assertThat(postRepository.findAll().get(0).getMessage()).isEqualTo("messaggio");
    }

    @Test
    @Transactional
        // for read posts of user
    void shoulPostForAExistingUser() {

        createSocialUser("username");

        Assertions.assertThat(socialUserRepository.count()).isEqualTo(1);
        Assertions.assertThat(postRepository.count()).isEqualTo(0);

        final List<ShowData> showData = target.doAction("username -> messaggio");


        Assertions.assertThat(showData).hasSize(0);
        Assertions.assertThat(socialUserRepository.count()).isEqualTo(1);
        Assertions.assertThat(postRepository.count()).isEqualTo(1);

        final Set<Post> posts = socialUserRepository.findByUsernameIgnoreCase("username").get().getPosts();
        Assertions.assertThat(posts).hasSize(1);
        final ArrayList<Post> postsArray = new ArrayList<>(posts);
        Assertions.assertThat(postsArray.get(0).getMessage()).isEqualTo("messaggio");
    }


}
