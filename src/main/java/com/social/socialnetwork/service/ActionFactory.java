package com.social.socialnetwork.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * factory that managed action of social network
 */
@Service
public class ActionFactory {

    private Action posting;

    private Action userPage;

    private Action following;

    private Action wall;

    public ActionFactory(Action posting, Action userPage, Action following, Action wall) {
        this.posting = posting;
        this.userPage = userPage;
        this.following = following;
        this.wall = wall;
    }

    @Transactional
    public List<ShowData> doAction(String action) {

        if (action.contains("->")) {
            return posting.action(action);
        }
        if (action.contains("follows")) {
            return following.action(action);
        }
        if (action.contains("wall")) {
            return wall.action(action);
        }

        // assumo che sia solamente il nome dell'utente
        return userPage.action(action);
    }
}
