package com.embosfer.katas.twitter.commands;

import com.embosfer.katas.twitter.cache.UserPostsCache;
import com.embosfer.katas.twitter.domain.Message;
import com.embosfer.katas.twitter.domain.User;
import com.embosfer.katas.twitter.out.MessageOutputter;

import java.time.Clock;

public class CommandFactory {

    private final MessageOutputter messageOutputter;
    private final UserPostsCache userPostsCache;
    private final Clock clock;

    public CommandFactory(MessageOutputter messageOutputter, UserPostsCache userPostsCache, Clock clock) {
        this.messageOutputter = messageOutputter;
        this.userPostsCache = userPostsCache;
        this.clock = clock;
    }

    public Command newCommand(String userInput) {

        if (userWantsToQuit(userInput)) {
            return new QuitCommand(messageOutputter);
        } else if (userWantsToPostMessage(userInput)) {
            PostCommand postCommand = createPostCommandFrom(userInput);
            userPostsCache.add(postCommand.message());
            return postCommand;
        } else if (userWantsToReadOtherUsersTimeline(userInput)) {
            User user = User.of(userInput);
            return new ReadUserTimelineCommand(user, userPostsCache.getTimelineFor(user), clock.instant(), messageOutputter);
        }

        return new UnknownCommand(userInput, messageOutputter);
    }

    private boolean userWantsToReadOtherUsersTimeline(String userInput) {
        String[] chunks = userInput.split(" ");
        return chunks.length == 1;
    }

    private PostCommand createPostCommandFrom(String userInput) {
        String[] chunks = userInput.split(" -> ");
        return new PostCommand(Message.of(chunks[1], User.of(chunks[0]), clock.instant()), messageOutputter);
    }

    private boolean userWantsToPostMessage(String userInput) {
        return userInput.contains(" -> ");
    }

    private boolean userWantsToQuit(String userInput) {
        return userInput.trim().equals("quit");
    }
}
