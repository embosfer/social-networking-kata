package com.embosfer.katas.twitter.commands;

import com.embosfer.katas.twitter.out.MessageOutputter;

public class QuitCommand implements Command {

    private final MessageOutputter messageOutputter;

    public QuitCommand(MessageOutputter messageOutputter) {
        this.messageOutputter = messageOutputter;
    }

    @Override
    public void execute() {
        messageOutputter.printOut("Bye!");
    }

}
