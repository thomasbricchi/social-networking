package com.social.socialnetwork.intTest;

import com.social.socialnetwork.entity.SocialUser;
import com.social.socialnetwork.service.ActionFactory;
import com.social.socialnetwork.service.ShowData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

class UserPageIntTest extends UtilsTests {


    @Autowired
    private ActionFactory target;

    @Test
    @Transactional
    void shoulShowMyPostIfExist() {
        Instant instant = Instant.parse("2019-01-03T10:15:30.00Z");
        setupClockMock(instant);
        final SocialUser socialUserWithPost = createSocialUserWithPost("username", "messaggio");


        instant = Instant.parse("2019-01-03T10:15:35.00Z");
        setupClockMock(instant);
        addNewPostToSocialUserAtInstant(instant, socialUserWithPost, "Messaggio 2");


        instant = Instant.parse("2019-01-03T10:15:40.00Z");
        setupClockMock(instant);
        final List<ShowData> showData = target.doAction("username");
        Assertions.assertThat(showData).hasSize(2);

        Assertions.assertThat(showData.get(0).print()).isEqualTo("Messaggio 2 ( 5 seconds ago )");
        Assertions.assertThat(showData.get(1).print()).isEqualTo("messaggio ( 10 seconds ago )");
    }

    @Test
    @Transactional
    void shoulNotShowMyPostIfNotExist() {
        createSocialUser("username");
        final List<ShowData> showData = target.doAction("username");
        Assertions.assertThat(showData).hasSize(0);
    }


}
