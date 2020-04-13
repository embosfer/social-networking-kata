package com.embosfer.katas.twitter.commands;

public class TwitterCommandInvoker {

    private final CommandFactory commandFactory;

    public TwitterCommandInvoker(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    public Command execute(String userInput) {

        Command command = commandFactory.newCommand(userInput);
        command.execute();

        return command;
    }
}
