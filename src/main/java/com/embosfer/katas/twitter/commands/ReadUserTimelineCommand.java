package com.embosfer.katas.twitter.commands;

import com.embosfer.katas.twitter.domain.Message;
import com.embosfer.katas.twitter.domain.User;
import com.embosfer.katas.twitter.out.MessageOutputter;

import java.util.List;

public class ReadUserTimelineCommand implements UserCommand {

    private final User user;
    private final List<Message> posts;
    private final MessageOutputter messageOutputter;

    public ReadUserTimelineCommand(User user, List<Message> posts, MessageOutputter messageOutputter) {
        this.user = user;
        this.posts = posts;
        this.messageOutputter = messageOutputter;
    }

    @Override
    public User user() {
        return user;
    }

    @Override
    public void execute() {
        messageOutputter.printOut(user.name);
        posts.forEach(post -> messageOutputter.printOut(post.value));
    }
}
