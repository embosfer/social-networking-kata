package com.embosfer.katas.twitter.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandTest {

    @Test
    void recognisesQuitCommands() {
        assertTrue(new QuitCommand().isQuitCommand());
        assertFalse(new UnknownCommand("some-input").isQuitCommand());
    }
}