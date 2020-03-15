package com.embosfer.katas.twitter.commands;

public class CommandFactory {

    public Command newCommand(String userInput) {

        if (userInput.trim().equals("quit")) {
            return new QuitCommand();
        }

        return new UnknownCommand(userInput);
    }
}
