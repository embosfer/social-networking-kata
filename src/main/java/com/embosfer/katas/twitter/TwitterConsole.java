package com.embosfer.katas.twitter;

import com.embosfer.katas.twitter.commands.TwitterCommandProcessor;
import com.embosfer.katas.twitter.in.UserInput;

public class TwitterConsole {

    private final UserInput userInput;
    private final TwitterCommandProcessor twitterCommandProcessor;

    public TwitterConsole(UserInput userInput, TwitterCommandProcessor twitterCommandProcessor) {
        this.userInput = userInput;
        this.twitterCommandProcessor = twitterCommandProcessor;
    }

    public void start() {
        twitterCommandProcessor.process(userInput.nextInput());
    }

}
