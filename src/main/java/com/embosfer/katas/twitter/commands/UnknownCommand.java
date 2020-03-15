package com.embosfer.katas.twitter.commands;

public class UnknownCommand implements Command {

    private final String userInput;

    public UnknownCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public String asOutMessage() {
        return String.format("Unknown command '%s': please try again.", userInput);
    }
}
