package com.embosfer.katas.twitter.cache;

import com.embosfer.katas.twitter.domain.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserSubscriptionsCache {

    Map<User, Collection<User>> userSubscriptions = new HashMap<>();

    public void add(User user, User followedUser) {
        userSubscriptions.compute(user, (u, followedUsers) -> {
            if (followedUsers == null) {
                followedUsers = new ArrayList<>();
            }
            followedUsers.add(followedUser);
            return followedUsers;
        });
    }
}
