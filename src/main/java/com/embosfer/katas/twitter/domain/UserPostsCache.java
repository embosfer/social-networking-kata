package com.embosfer.katas.twitter.domain;

import com.embosfer.katas.twitter.commands.PostCommand;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class UserPostsCache {

    private final Map<User, LinkedList<Message>> commandsPerUser = new HashMap<>();

    public void add(PostCommand userPostCommand) {
        commandsPerUser.compute(userPostCommand.user(), (user, userPosts) -> {
            if (userPosts == null) {
                userPosts = new LinkedList<>();
            }
            userPosts.addFirst(userPostCommand.message());
            return userPosts;
        });
    }

    public List<Message> getTimelineFor(User user) {
        return commandsPerUser.get(user);
    }
}
