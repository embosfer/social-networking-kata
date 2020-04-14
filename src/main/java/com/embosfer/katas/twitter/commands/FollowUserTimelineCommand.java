package com.embosfer.katas.twitter.commands;

import com.embosfer.katas.twitter.domain.User;
import com.embosfer.katas.twitter.out.MessageOutputter;

public class FollowUserTimelineCommand implements Command {

    private final User user;
    private final User followedUser;
    private final MessageOutputter messageOutputter;

    public FollowUserTimelineCommand(User user, User followedUser, MessageOutputter messageOutputter) {
        this.user = user;
        this.followedUser = followedUser;
        this.messageOutputter = messageOutputter;
    }

    @Override
    public void execute() {
        messageOutputter.printOut(String.format("%s follows %s", user.name, followedUser.name));
    }
}
