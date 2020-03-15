package com.embosfer.katas.twitter.commands;

import com.embosfer.katas.twitter.domain.Message;
import com.embosfer.katas.twitter.domain.User;

public class CommandFactory {

    public Command newCommand(String userInput) {

        if (userWantsToQuit(userInput)) {
            return new QuitCommand();
        } else if (userWantsToPostMessage(userInput)) {
            return createPostCommandFrom(userInput);
        }

        return new UnknownCommand(userInput);
    }

    private Command createPostCommandFrom(String userInput) {
        String[] chunks = userInput.split(" -> ");
        return new PostCommand(User.of(chunks[0]), Message.of(chunks[1]));
    }

    private boolean userWantsToPostMessage(String userInput) {
        return userInput.contains(" -> ");
    }

    private boolean userWantsToQuit(String userInput) {
        return userInput.trim().equals("quit");
    }
}
