package com.embosfer.katas.twitter.domain;

import java.util.Objects;

public class Message {
    public final String value;

    private Message(String value) {
        this.value = value;
    }

    public static Message of(String message) {
        return new Message(message);
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
