package com.embosfer.katas.twitter.domain;

import java.time.Duration;
import java.time.Instant;

public class Message {
    public final String value;
    public final User user;
    public final Instant instant;

    private Message(String value, User user, Instant instant) {
        this.value = value;
        this.user = user;
        this.instant = instant;
    }

    public static Message of(String message, User user, Instant instant) {
        return new Message(message, user, instant);
    }

    @Override
    public String toString() {
        return value;
    }

    public String formatWithTimeElapsed(Instant now) {
        return String.format("%s (%s ago)", value, durationSincePost(now));
    }

    private String durationSincePost(Instant now) {
        Duration duration = Duration.between(instant, now);
        if (duration.toDays() > 0) {
            return duration.toDays() + " days";
        }
        if (duration.toHours() > 0) {
            return duration.toHours() + " hours";
        }
        if (duration.toMinutes() > 0) {
            return duration.toMinutes() + " minutes";
        }
        return duration.toSeconds() + " seconds";
    }
}
