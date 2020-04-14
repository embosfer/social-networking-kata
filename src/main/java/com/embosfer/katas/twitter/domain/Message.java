package com.embosfer.katas.twitter.domain;

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

}
