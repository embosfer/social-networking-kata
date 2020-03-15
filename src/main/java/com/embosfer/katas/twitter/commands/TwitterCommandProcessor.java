package com.embosfer.katas.twitter.commands;

import com.embosfer.katas.twitter.out.MessageOutputter;

public class TwitterCommandProcessor {

    private final CommandFactory commandFactory;
    private final MessageOutputter messageOutputter;

    public TwitterCommandProcessor(CommandFactory commandFactory, MessageOutputter messageOutputter) {
        this.commandFactory = commandFactory;
        this.messageOutputter = messageOutputter;
    }

    public Command process(String userInput) {

        Command command = commandFactory.newCommand(userInput);
        messageOutputter.printOut(command.asOutMessage());

        return command;
    }
}
