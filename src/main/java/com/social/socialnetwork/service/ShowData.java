package com.social.socialnetwork.service;

import java.time.Instant;

/**
 * interface for output of @{@link ActionFactory}
 */
public interface ShowData {

    /**
     * Print the label to expose
     *
     * @return the label to show in console
     */
    String print();

    Instant getData();
}
