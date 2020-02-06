package com.social.socialnetwork.service;

import com.social.socialnetwork.SocialUserRepository;
import com.social.socialnetwork.domain.Post;
import com.social.socialnetwork.domain.SocialUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * 2020/02/06
 *
 * @author Sirius
 */
@Service
public class ActionFactory {

    private final SocialUserRepository socialUserRepository;

    public ActionFactory(SocialUserRepository socialUserRepository) {
        this.socialUserRepository = socialUserRepository;
    }

    @Transactional
    public void print() {

        final SocialUser socialUser = new SocialUser();
        socialUser.setUsername("Alice");
        final Post post = new Post();
        post.setMessage("Ciao");
        final Set<Post> post1 = new HashSet<>();
        post1.add(post);
        socialUser.setPosts(post1);


        socialUserRepository.save(socialUser);
        for (SocialUser user : socialUserRepository.findAll()) {
            System.out.println(user.toString());
        }

    }
}
