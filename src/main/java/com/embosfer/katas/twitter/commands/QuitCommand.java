package com.embosfer.katas.twitter.commands;

public class QuitCommand implements Command {

    @Override
    public String asOutMessage() {
        return "Bye!";
    }

}
