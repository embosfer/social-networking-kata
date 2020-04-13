package com.embosfer.katas.twitter.commands;

import com.embosfer.katas.twitter.domain.Message;
import com.embosfer.katas.twitter.domain.User;
import com.embosfer.katas.twitter.domain.UserPostsCache;
import com.embosfer.katas.twitter.out.MessageOutputter;

public class CommandFactory {

    private final MessageOutputter messageOutputter;
    private final UserPostsCache userPostsCache;

    public CommandFactory(MessageOutputter messageOutputter, UserPostsCache userPostsCache) {
        this.messageOutputter = messageOutputter;
        this.userPostsCache = userPostsCache;
    }

    public Command newCommand(String userInput) {

        if (userWantsToQuit(userInput)) {
            return new QuitCommand(messageOutputter);
        } else if (userWantsToPostMessage(userInput)) {
            userPostsCache.add(createPostCommandFrom(userInput));
            return createPostCommandFrom(userInput);
        } else if (userWantsToReadOtherUsersTimeline(userInput)) {
            User user = User.of(userInput);
            return new ReadUserTimelineCommand(user, userPostsCache.getTimelineFor(user), messageOutputter);
        }

        return new UnknownCommand(userInput, messageOutputter);
    }

    private boolean userWantsToReadOtherUsersTimeline(String userInput) {
        String[] chunks = userInput.split(" ");
        return chunks.length == 1;
    }

    private PostCommand createPostCommandFrom(String userInput) {
        String[] chunks = userInput.split(" -> ");
        return new PostCommand(User.of(chunks[0]), Message.of(chunks[1]), messageOutputter);
    }

    private boolean userWantsToPostMessage(String userInput) {
        return userInput.contains(" -> ");
    }

    private boolean userWantsToQuit(String userInput) {
        return userInput.trim().equals("quit");
    }
}
