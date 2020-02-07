package com.social.socialnetwork.service;

import com.social.socialnetwork.domain.PostWithTime;
import com.social.socialnetwork.entity.SocialUser;
import com.social.socialnetwork.reporistory.SocialUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserPage implements Action {
    private final SocialUserRepository socialUserRepository;
    private final Clock clock;

    public UserPage(SocialUserRepository socialUserRepository, Clock clock) {
        this.socialUserRepository = socialUserRepository;
        this.clock = clock;
    }

    @Override
    @Transactional
    public List<ShowData> action(String input) {
        // assumo che cerco un nome esistente
        final SocialUser socialUser = socialUserRepository.findByUsernameIgnoreCase(input).get();

        return socialUser.getPosts().stream().
            map(post -> new PostWithTime(post, clock))
            .sorted(Comparator.comparing(ShowData::getData).reversed())
            .collect(Collectors.toList());

    }
}
