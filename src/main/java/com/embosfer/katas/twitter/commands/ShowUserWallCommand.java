package com.embosfer.katas.twitter.commands;

import com.embosfer.katas.twitter.domain.User;
import com.embosfer.katas.twitter.out.MessageOutputter;

public class ShowUserWallCommand implements Command {
    private final User user;
    private final MessageOutputter messageOutputter;

    public ShowUserWallCommand(User user, MessageOutputter messageOutputter) {
        this.user = user;
        this.messageOutputter = messageOutputter;
    }

    @Override
    public void execute() {
        messageOutputter.printOut(user.name + " wall");
    }
}
