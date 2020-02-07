package com.social.socialnetwork.reporistory;

import com.social.socialnetwork.entity.SocialUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SocialUserRepository extends JpaRepository<SocialUser, Long> {

    Optional<SocialUser> findByUsernameIgnoreCase(String username);

}
