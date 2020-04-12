package com.embosfer.katas.twitter.domain;

import java.util.Objects;

public class User {
    public final String name;

    private User(String name) {
        this.name = name;
    }

    public static User of(String name) {
        return new User(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
