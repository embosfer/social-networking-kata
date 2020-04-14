package com.embosfer.katas.twitter.commands;

import com.embosfer.katas.twitter.cache.UserPostsCache;
import com.embosfer.katas.twitter.cache.UserSubscriptionsCache;
import com.embosfer.katas.twitter.domain.User;
import com.embosfer.katas.twitter.out.MessageOutputter;

import java.time.Instant;
import java.util.Collection;

import static java.util.Comparator.comparing;
import static java.util.Comparator.reverseOrder;

public class ShowUserWallCommand implements Command {
    private final User user;
    private final UserPostsCache userPostsCache;
    private final UserSubscriptionsCache userSubscriptionsCache;
    private final Instant instant;
    private final MessageOutputter messageOutputter;

    public ShowUserWallCommand(User user,
                               UserPostsCache userPostsCache, UserSubscriptionsCache userSubscriptionsCache,
                               Instant instant,
                               MessageOutputter messageOutputter) {
        this.user = user;
        this.userPostsCache = userPostsCache;
        this.userSubscriptionsCache = userSubscriptionsCache;
        this.instant = instant;
        this.messageOutputter = messageOutputter;
    }

    @Override
    public void execute() {
        messageOutputter.printOut(user.name + " wall");

        userSubscriptionsCache.getSubscriptionsFor(user)
                .stream()
                .map(userPostsCache::getTimelineFor)
                .flatMap(Collection::stream)
                .sorted(comparing(message -> message.instant, reverseOrder()))
                .forEach(message -> messageOutputter.printOut(message.user.name + " - " + message.value));
    }
}
