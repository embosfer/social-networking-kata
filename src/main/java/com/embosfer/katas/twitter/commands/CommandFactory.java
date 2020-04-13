package com.embosfer.katas.twitter.commands;

import com.embosfer.katas.twitter.domain.Message;
import com.embosfer.katas.twitter.domain.User;
import com.embosfer.katas.twitter.out.MessageOutputter;

public class CommandFactory {

    private final MessageOutputter messageOutputter;

    public CommandFactory(MessageOutputter messageOutputter) {
        this.messageOutputter = messageOutputter;
    }

    public Command newCommand(String userInput) {

        if (userWantsToQuit(userInput)) {
            return new QuitCommand(messageOutputter);
        } else if (userWantsToPostMessage(userInput)) {
            return createPostCommandFrom(userInput);
        }

        return new UnknownCommand(userInput, messageOutputter);
    }

    private Command createPostCommandFrom(String userInput) {
        String[] chunks = userInput.split(" -> ");
        return new PostCommand(User.of(chunks[0]), Message.of(chunks[1]), messageOutputter);
    }

    private boolean userWantsToPostMessage(String userInput) {
        return userInput.contains(" -> ");
    }

    private boolean userWantsToQuit(String userInput) {
        return userInput.trim().equals("quit");
    }
}
