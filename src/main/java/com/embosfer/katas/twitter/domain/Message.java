package com.embosfer.katas.twitter.domain;

import java.time.Instant;
import java.util.Objects;

public class Message {
    public final String value;
    public final Instant instant;

    private Message(String value, Instant instant) {
        this.value = value;
        this.instant = instant;
    }

    public static Message of(String message, Instant instant) {
        return new Message(message, instant);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return Objects.equals(value, message1.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }

}
