package com.embosfer.katas.twitter.cache;

import com.embosfer.katas.twitter.domain.Message;
import com.embosfer.katas.twitter.domain.User;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class UserPostsCache {

    private final Map<User, LinkedList<Message>> postsPerUser = new HashMap<>();

    public void add(Message post) {
        postsPerUser.compute(post.user, (user, userPosts) -> {
            if (userPosts == null) {
                userPosts = new LinkedList<>();
            }
            userPosts.addFirst(post);
            return userPosts;
        });
    }

    public List<Message> getTimelineFor(User user) {
        return postsPerUser.get(user);
    }
}
