package com.embosfer.katas.twitter.commands;

import com.embosfer.katas.twitter.domain.Message;
import com.embosfer.katas.twitter.out.MessageOutputter;

public class PostCommand implements Command {
    private final Message message;
    private final MessageOutputter messageOutputter;

    public PostCommand(Message message, MessageOutputter messageOutputter) {
        this.message = message;
        this.messageOutputter = messageOutputter;
    }

    @Override
    public void execute() {
        messageOutputter.printOut(String.format("%s -> %s", message.user.name, message));
    }

    public Message message() {
        return message;
    }
}
