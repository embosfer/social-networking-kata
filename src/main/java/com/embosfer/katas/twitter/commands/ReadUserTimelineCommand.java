package com.embosfer.katas.twitter.commands;

import com.embosfer.katas.twitter.domain.Message;
import com.embosfer.katas.twitter.domain.User;
import com.embosfer.katas.twitter.out.MessageOutputter;

import java.time.Duration;
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
        posts.forEach(post -> messageOutputter.printOut(withTime(post)));
    }

    private String withTime(Message post) {
        return String.format("%s (%s ago)", post.value, durationSincePost(post));
    }

    private String durationSincePost(Message post) {
        Duration duration = Duration.between(post.instant, now);
        if (duration.toDays() > 0) {
            return duration.toDays() + " days";
        }
        if (duration.toHours() > 0) {
            return duration.toHours() + " hours";
        }
        if (duration.toMinutes() > 0) {
            return duration.toMinutes() + " minutes";
        }
        return duration.toSeconds() + " seconds";
    }
}
