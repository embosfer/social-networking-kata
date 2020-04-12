package com.embosfer.katas.twitter.domain;

import java.util.Objects;

public class Message {
    private final String message;

    private Message(String message) {
        this.message = message;
    }

    public static Message of(String message) {
        return new Message(message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return Objects.equals(message, message1.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return message;
    }
}
