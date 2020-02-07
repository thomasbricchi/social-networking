package com.social.socialnetwork.service;

import com.social.socialnetwork.entity.Follow;
import com.social.socialnetwork.entity.SocialUser;
import com.social.socialnetwork.reporistory.SocialUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * service that managed following
 */
@Service
public class Following implements Action {
    private final SocialUserRepository socialUserRepository;

    public Following(SocialUserRepository socialUserRepository) {
        this.socialUserRepository = socialUserRepository;
    }

    @Override
    @Transactional
    public List<ShowData> action(String input) {

        String[] parts = input.split("follows");
        String follower = parts[0].trim();
        String followed = parts[1].trim();


        // assumo che i due utenti esistono
        final SocialUser socialUser = socialUserRepository.findByUsernameIgnoreCase(follower).get();


        final Follow follow = createFollowEntity(followed, socialUser);

        socialUser.addFollower(follow);
        socialUserRepository.save(socialUser);

        return Arrays.asList();

    }

    private Follow createFollowEntity(String followed, SocialUser socialUser) {
        final Follow follow = new Follow();
        follow.setFollowedUser(socialUserRepository.findByUsernameIgnoreCase(followed).get());
        follow.setSocialUsers(socialUser);
        return follow;
    }
}
