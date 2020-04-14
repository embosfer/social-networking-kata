package com.embosfer.katas.twitter.commands;

import com.embosfer.katas.twitter.cache.UserPostsCache;
import com.embosfer.katas.twitter.domain.Message;
import com.embosfer.katas.twitter.domain.User;
import com.embosfer.katas.twitter.out.MessageOutputter;

import java.time.Clock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandFactory {

    private static final Pattern POST_MESSAGE_PATTERN = Pattern.compile("(?<user>\\w+) -> (?<message>.*+)");
    private static final Pattern FOLLOW_USER_PATTERN = Pattern.compile("(?<user>\\w+) follows (?<followedUser>\\w+)");

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
        } else if (POST_MESSAGE_PATTERN.asMatchPredicate().test(userInput)) {
            Matcher matcher = POST_MESSAGE_PATTERN.matcher(userInput);
            if (matcher.find()) {
                PostCommand postCommand = new PostCommand(Message.of(matcher.group("message"), User.of(matcher.group("user")), clock.instant()), messageOutputter);
                userPostsCache.add(postCommand.message());
                return postCommand;
            }
        } else if (userWantsToReadOtherUsersTimeline(userInput)) {
            User user = User.of(userInput);
            return new ReadUserTimelineCommand(user, userPostsCache.getTimelineFor(user), clock.instant(), messageOutputter);
        } else if (FOLLOW_USER_PATTERN.asMatchPredicate().test(userInput)) {
            Matcher matcher = FOLLOW_USER_PATTERN.matcher(userInput);
            if (matcher.find()) {
                return new FollowUserTimelineCommand(User.of(matcher.group("user")), User.of(matcher.group("followedUser")), messageOutputter);
            }
        }

        return new UnknownCommand(userInput, messageOutputter);
    }

    private boolean userWantsToReadOtherUsersTimeline(String userInput) {
        String[] chunks = userInput.split(" ");
        return chunks.length == 1;
    }

    private boolean userWantsToQuit(String userInput) {
        return userInput.trim().equals("quit");
    }
}
