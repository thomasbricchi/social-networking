package com.social.socialnetwork.intTest;

import com.social.socialnetwork.entity.Follow;
import com.social.socialnetwork.entity.SocialUser;
import com.social.socialnetwork.service.ActionMaker;
import com.social.socialnetwork.service.ShowData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;


class FollowingIntTest extends UtilsTests {

    @Autowired
    private ActionMaker target;

    @Test
    @Transactional
    void shouldAddToMyFollowed() {
        createSocialUser("user1");
        final SocialUser user2 = createSocialUser("user2");
        final List<ShowData> showData = target.doAction("user1 follows user2");
        Assertions.assertThat(showData).hasSize(0);

        final Set<Follow> userFollow = socialUserRepository.findByUsernameIgnoreCase("user1").get().getFollowers();

        Assertions.assertThat(userFollow).hasSize(1);
        Assertions.assertThat(userFollow.stream().findFirst().get().getFollowedUser()).isEqualTo(user2);
    }

}
