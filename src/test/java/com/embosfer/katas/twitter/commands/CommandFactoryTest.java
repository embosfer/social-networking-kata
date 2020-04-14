package com.embosfer.katas.twitter.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CommandFactoryTest {

    CommandFactory commandFactory;

    @BeforeEach
    void setUp() {
        commandFactory = new CommandFactory(null, null, null, null);
    }

    @Test
    void identifiesUnknownCommands() {
        UnknownCommand command = (UnknownCommand) commandFactory.newCommand("dont know this command");

        assertFalse(command.isQuitCommand());
        assertThat(command).isInstanceOf(UnknownCommand.class);
    }

}