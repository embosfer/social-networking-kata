package com.embosfer.katas.twitter.cache;

import com.embosfer.katas.twitter.domain.User;

import java.util.*;

public class UserSubscriptionsCache {

    Map<User, Set<User>> userSubscriptions = new HashMap<>();

    public void add(User user, User followedUser) {
        userSubscriptions.compute(user, (u, followedUsers) -> {
            if (followedUsers == null) {
                followedUsers = new HashSet<>();
                followedUsers.add(user); // adding itself as well
            }
            followedUsers.add(followedUser);
            return followedUsers;
        });
    }

    public Collection<User> getSubscriptionsFor(User user) {
        return userSubscriptions.get(user);
    }
}
