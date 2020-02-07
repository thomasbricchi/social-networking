package com.social.socialnetwork;

import com.social.socialnetwork.domain.SocialUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 2020/02/06
 *
 * @author Sirius
 */
@Repository
public interface SocialUserRepository extends JpaRepository<SocialUser, Long> {

}
