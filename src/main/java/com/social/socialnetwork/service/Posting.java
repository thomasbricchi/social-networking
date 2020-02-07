package com.social.socialnetwork.service;

import com.social.socialnetwork.entity.Post;
import com.social.socialnetwork.entity.SocialUser;
import com.social.socialnetwork.reporistory.SocialUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * service that managed the posting of messages
 */
@Service
public class Posting implements Action {

    private final SocialUserRepository socialUserRepository;
    private final Clock clock;

    public Posting(SocialUserRepository socialUserRepository, Clock clock) {
        this.socialUserRepository = socialUserRepository;
        this.clock = clock;
    }

    @Override
    @Transactional
    public List<ShowData> action(String input) {


        String[] parts = input.split("->");
        String username = parts[0].trim();
        String message = parts[1].trim();


        final SocialUser socialUsers = socialUserRepository.findByUsernameIgnoreCase(username).orElseGet(createSocialUser(username));

        final Post post = createPostEntity(message, socialUsers);

        socialUsers.addPost(post);

        return Arrays.asList();

    }

    private Post createPostEntity(String message, SocialUser socialUsers) {
        final Post post = new Post();
        post.setMessage(message);
        post.setCreationDate(Instant.now(clock));
        post.setSocialUsers(socialUsers);
        return post;
    }

    private Supplier<SocialUser> createSocialUser(String username) {
        return () -> {
            final SocialUser socialUser = new SocialUser();
            socialUser.setUsername(username);
            socialUserRepository.save(socialUser);
            return socialUser;
        };
    }
}
