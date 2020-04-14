package com.embosfer.katas.twitter.commands;

import com.embosfer.katas.twitter.domain.Message;
import com.embosfer.katas.twitter.domain.User;
import com.embosfer.katas.twitter.out.MessageOutputter;

import java.time.Instant;
import java.util.List;

public class ReadUserTimelineCommand implements Command {

    private final User user;
    private final List<Message> posts;
    private final Instant now;
    private final MessageOutputter messageOutputter;

    public ReadUserTimelineCommand(User user, List<Message> posts, Instant now, MessageOutputter messageOutputter) {
        this.user = user;
        this.posts = posts;
        this.now = now;
        this.messageOutputter = messageOutputter;
    }

    @Override
    public void execute() {
        messageOutputter.printOut(user.name);
        posts.forEach(post -> messageOutputter.printOut(post.formatWithTimeElapsed(now)));
    }

}
