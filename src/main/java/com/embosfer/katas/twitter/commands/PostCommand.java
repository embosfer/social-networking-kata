package com.embosfer.katas.twitter.commands;

import com.embosfer.katas.twitter.domain.Message;
import com.embosfer.katas.twitter.domain.User;

public class PostCommand implements Command {
    private final User user;
    private final Message message;

    public PostCommand(User user, Message message) {
        this.user = user;
        this.message = message;
    }

    public String asOutMessage() {
        return String.format("%s -> %s", user.name, message);
    }
}
