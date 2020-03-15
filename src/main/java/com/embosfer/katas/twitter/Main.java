package com.embosfer.katas.twitter;

import com.embosfer.katas.twitter.commands.CommandFactory;
import com.embosfer.katas.twitter.commands.TwitterCommandProcessor;
import com.embosfer.katas.twitter.in.StdInUserInput;
import com.embosfer.katas.twitter.out.StdOutMessageOutputter;

public class Main {

    public static void main(String[] args) {

        TwitterConsole twitterConsole = new TwitterConsole(
                new StdInUserInput(),
                new TwitterCommandProcessor(
                        new CommandFactory(),
                        new StdOutMessageOutputter()));

        twitterConsole.start();
    }
}
