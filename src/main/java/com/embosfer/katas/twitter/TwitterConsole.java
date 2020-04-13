package com.embosfer.katas.twitter;

import com.embosfer.katas.twitter.commands.Command;
import com.embosfer.katas.twitter.commands.TwitterCommandInvoker;
import com.embosfer.katas.twitter.in.UserInput;

public class TwitterConsole {

    private final UserInput userInput;
    private final TwitterCommandInvoker twitterCommandInvoker;

    public TwitterConsole(UserInput userInput, TwitterCommandInvoker twitterCommandInvoker) {
        this.userInput = userInput;
        this.twitterCommandInvoker = twitterCommandInvoker;
    }

    public void start() {

        Command command;
        do {
            command = twitterCommandInvoker.execute(userInput.nextInput());
        } while (!command.isQuitCommand());
    }

}
