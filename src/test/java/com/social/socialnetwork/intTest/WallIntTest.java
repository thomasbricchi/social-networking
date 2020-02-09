package com.social.socialnetwork.intTest;

import com.social.socialnetwork.entity.Follow;
import com.social.socialnetwork.entity.SocialUser;
import com.social.socialnetwork.service.ActionMaker;
import com.social.socialnetwork.service.ShowData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;


class WallIntTest extends UtilsTests {


    @Autowired
    private ActionMaker target;


    @Test
    @Transactional
    void shouldReturnAllPostsOfMyWallOrderByDate() {

        // GIVEN
        Instant instant = Instant.parse("2019-01-03T10:15:30.00Z");
        setupClockMock(instant);

        final SocialUser user1 = createSocialUserWithPost("user1", "ciao a tutti");

        instant = Instant.parse("2019-01-03T10:15:35.00Z");
        setupClockMock(instant);

        final SocialUser user2 = createSocialUserWithPost("user2", "post utente 2");

        instant = Instant.parse("2019-01-03T10:10:00.00Z");
        setupClockMock(instant);
        final SocialUser user3 = createSocialUserWithPost("user3", "post utente 3");


        instant = Instant.parse("2019-01-01T10:10:00.00Z");
        setupClockMock(instant);
        createSocialUserWithPost("user4", "post utente 3");

        follow(user1, user2);
        follow(user1, user3);

        instant = Instant.parse("2019-01-03T10:15:40.00Z");
        setupClockMock(instant);

        // WHEN
        final List<ShowData> wall = target.doAction("user1 wall");

        // THEN
        final List<String> messageToShow = wall.stream().map(ShowData::print).collect(Collectors.toList());
        Assertions.assertThat(messageToShow).hasSize(3);
        Assertions.assertThat(messageToShow.get(0)).isEqualTo("user2 -> post utente 2 ( 5 seconds ago )");
        Assertions.assertThat(messageToShow.get(1)).isEqualTo("ciao a tutti ( 10 seconds ago )");
        Assertions.assertThat(messageToShow.get(2)).isEqualTo("user3 -> post utente 3 ( 5 minutes ago )");
    }

    private SocialUser follow(SocialUser user1, SocialUser user2) {
        final Follow follow = new Follow();
        follow.setFollowedUser(user2);
        user1.addFollower(follow);
        return socialUserRepository.save(user1);


    }


}
