package com.social.socialnetwork.intTest;

import com.social.socialnetwork.SocialNetworkApplicationTest;
import com.social.socialnetwork.entity.Post;
import com.social.socialnetwork.entity.SocialUser;
import com.social.socialnetwork.reporistory.FollowRepository;
import com.social.socialnetwork.reporistory.PostRepository;
import com.social.socialnetwork.reporistory.SocialUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.TimeZone;

/**
 * 2020/02/07
 *
 * @author Sirius
 */
@SpringBootTest(classes = SocialNetworkApplicationTest.class)
public class UtilsTests {
    @MockBean
    protected Clock clock;

    @Autowired
    protected SocialUserRepository socialUserRepository;

    @Autowired
    protected PostRepository postRepository;

    @Autowired
    protected FollowRepository followRepository;

    protected SocialUser createSocialUser(String username) {
        final SocialUser socialUser = new SocialUser();
        socialUser.setUsername(username);
        return socialUserRepository.save(socialUser);
    }

    protected SocialUser createSocialUserWithPost(String username, String message) {
        final SocialUser socialUser = new SocialUser();
        socialUser.setUsername(username);
        socialUserRepository.save(socialUser);
        final Post post = new Post();
        post.setMessage(message);
        post.setSocialUsers(socialUser);
        post.setCreationDate(Instant.now(clock));
        socialUser.addPost(post);
        return socialUserRepository.save(socialUser);
    }

    @BeforeEach
    void setUp() {
        Instant instant = Instant.now();
        BDDMockito.when(clock.instant()).thenReturn(instant);
        BDDMockito.when(clock.getZone()).thenReturn(TimeZone.getTimeZone(ZoneOffset.UTC).toZoneId());
    }

    protected void setupClockMock(Instant instant) {
        BDDMockito.when(clock.instant()).thenReturn(instant);
        BDDMockito.when(clock.getZone()).thenReturn(TimeZone.getTimeZone(ZoneOffset.UTC).toZoneId());
    }


    protected void addNewPostToSocialUserAtInstant(Instant instant, SocialUser socialUserWithPost, String message) {
        final Post post = new Post();
        post.setMessage(message);
        post.setSocialUsers(socialUserWithPost);
        post.setCreationDate(instant);

        socialUserWithPost.addPost(post);
        socialUserRepository.save(socialUserWithPost);
    }

    @AfterEach
    void tearDown() {
        postRepository.deleteAll();
        followRepository.deleteAll();
        socialUserRepository.deleteAll();

    }
}
