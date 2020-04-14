package com.embosfer.katas.twitter.commands;

import com.embosfer.katas.twitter.cache.UserPostsCache;
import com.embosfer.katas.twitter.cache.UserSubscriptionsCache;
import com.embosfer.katas.twitter.domain.Message;
import com.embosfer.katas.twitter.domain.User;
import com.embosfer.katas.twitter.out.MessageOutputter;

import java.time.Clock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandFactory {

    private static final Pattern POST_MESSAGE_PATTERN = Pattern.compile("(?<user>\\w+) -> (?<message>.*+)");
    private static final Pattern READ_TIMELINE_PATTERN = Pattern.compile("(?<user>\\w+)");
    private static final Pattern FOLLOW_USER_PATTERN = Pattern.compile("(?<user>\\w+) follows (?<followedUser>\\w+)");

    private final MessageOutputter messageOutputter;
    private final UserPostsCache userPostsCache;
    private final UserSubscriptionsCache userSubscriptionsCache;
    private final Clock clock;

    public CommandFactory(MessageOutputter messageOutputter, UserPostsCache userPostsCache, UserSubscriptionsCache userSubscriptionsCache, Clock clock) {
        this.messageOutputter = messageOutputter;
        this.userPostsCache = userPostsCache;
        this.userSubscriptionsCache = userSubscriptionsCache;
        this.clock = clock;
    }

    public Command newCommand(String userInput) {

        if (userWantsToQuit(userInput)) {
            return new QuitCommand(messageOutputter);
        }

        if (POST_MESSAGE_PATTERN.asMatchPredicate().test(userInput)) {
            Matcher matcher = POST_MESSAGE_PATTERN.matcher(userInput);
            if (matcher.find()) {
                PostCommand postCommand = new PostCommand(Message.of(matcher.group("message"), User.of(matcher.group("user")), clock.instant()), messageOutputter);
                userPostsCache.add(postCommand.message());
                return postCommand;
            }
        }

        if (READ_TIMELINE_PATTERN.asMatchPredicate().test(userInput)) {
            User user = User.of(userInput);
            return new ReadUserTimelineCommand(user, userPostsCache.getTimelineFor(user), clock.instant(), messageOutputter);
        }

        if (FOLLOW_USER_PATTERN.asMatchPredicate().test(userInput)) {
            Matcher matcher = FOLLOW_USER_PATTERN.matcher(userInput);
            if (matcher.find()) {
                User user = User.of(matcher.group("user"));
                User followedUser = User.of(matcher.group("followedUser"));
                userSubscriptionsCache.add(user, followedUser);
                return new FollowUserTimelineCommand(user, followedUser, messageOutputter);
            }
        }

        return new UnknownCommand(userInput, messageOutputter);
    }

    private boolean userWantsToQuit(String userInput) {
        return userInput.trim().equals("quit");
    }
}
