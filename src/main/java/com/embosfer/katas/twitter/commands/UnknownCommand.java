package com.embosfer.katas.twitter.commands;

import com.embosfer.katas.twitter.out.MessageOutputter;

public class UnknownCommand implements Command {

    private final String userInput;
    private final MessageOutputter messageOutputter;

    public UnknownCommand(String userInput, MessageOutputter messageOutputter) {
        this.userInput = userInput;
        this.messageOutputter = messageOutputter;
    }

    @Override
    public void execute() {
        messageOutputter.printOut(String.format("Unknown command '%s': please try again.", userInput));
    }
}
