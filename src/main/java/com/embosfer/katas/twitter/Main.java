package com.embosfer.katas.twitter;

import com.embosfer.katas.twitter.commands.CommandFactory;
import com.embosfer.katas.twitter.commands.TwitterCommandInvoker;
import com.embosfer.katas.twitter.domain.UserPostsCache;
import com.embosfer.katas.twitter.in.StdInUserInput;
import com.embosfer.katas.twitter.out.StdOutMessageOutputter;

public class Main {

    public static void main(String[] args) {

        TwitterConsole twitterConsole = new TwitterConsole(
                new StdInUserInput(),
                new TwitterCommandInvoker(
                        new CommandFactory(new StdOutMessageOutputter(), new UserPostsCache())));

        twitterConsole.start();
    }
}
