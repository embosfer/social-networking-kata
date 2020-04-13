package com.embosfer.katas.twitter.commands;

public interface Command {

    default boolean isQuitCommand() {
        return (this instanceof QuitCommand);
    }

    void execute();
}
