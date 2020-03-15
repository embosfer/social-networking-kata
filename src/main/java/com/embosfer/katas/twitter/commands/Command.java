package com.embosfer.katas.twitter.commands;

public interface Command {

    String asOutMessage();

    default boolean isQuitCommand() {
        return (this instanceof QuitCommand);
    }
}
