package com.social.socialnetwork.service;

import java.util.List;


public interface Action {

    /**
     * perform action about input
     *
     * @param action the requested action
     * @return a list of @{@link ShowData} with message to print
     */
    List<ShowData> action(String action);
}
