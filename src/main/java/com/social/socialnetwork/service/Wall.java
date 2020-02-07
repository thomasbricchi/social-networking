package com.social.socialnetwork.service;

import com.social.socialnetwork.domain.PostWithTime;
import com.social.socialnetwork.domain.PostWithUserAndTime;
import com.social.socialnetwork.entity.SocialUser;
import com.social.socialnetwork.reporistory.SocialUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class Wall implements Action {
    private final SocialUserRepository socialUserRepository;
    private final Clock clock;

    public Wall(SocialUserRepository socialUserRepository, Clock clock) {
        this.socialUserRepository = socialUserRepository;
        this.clock = clock;
    }

    @Override
    @Transactional
    public List<ShowData> action(String input) {

        String[] parts = input.split("wall");
        String wallPage = parts[0].trim();

        final SocialUser socialUser = socialUserRepository.findByUsernameIgnoreCase(wallPage).get();

        return getPostToShowOrderedByCreationDate(socialUser);

    }

    private List<ShowData> getPostToShowOrderedByCreationDate(SocialUser socialUser) {

        List<ShowData> myPost = getAllPosts(socialUser);
        return orderPostsToShowByCreationDate(myPost);
    }

    private List<ShowData> orderPostsToShowByCreationDate(List<ShowData> myPost) {
        return myPost.stream().sorted(Comparator.comparing(ShowData::getData).reversed()).collect(Collectors.toList());
    }

    private List<ShowData> getAllPosts(SocialUser socialUser) {
        List<ShowData> myPost = getPostAboutSocialUser(socialUser);

        getStreamOfFollowerPosts(socialUser)
            .forEach(followedPost -> myPost.addAll(new ArrayList<>(followedPost)));
        return myPost;
    }

    private List<ShowData> getPostAboutSocialUser(SocialUser socialUser) {
        return new ArrayList<>(socialUser.getPosts())
            .stream()
            .map(post -> new PostWithTime(post, clock))
            .collect(Collectors.toList());
    }

    private Stream<List<PostWithUserAndTime>> getStreamOfFollowerPosts(SocialUser socialUser) {
        return socialUser.getFollowers()
            .stream()
            .map(follow -> follow.getFollowedUser().getPosts())
            .map(followPost -> followPost.stream().map(post -> new PostWithUserAndTime(post, clock)).collect(Collectors.toList()));
    }
}
