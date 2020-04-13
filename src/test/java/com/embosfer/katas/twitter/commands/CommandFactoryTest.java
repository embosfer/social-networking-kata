package com.embosfer.katas.twitter.commands;

import com.embosfer.katas.twitter.out.MessageOutputter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class CommandFactoryTest {

    CommandFactory commandFactory;
    MessageOutputter messageOutputter = mock(MessageOutputter.class);

    @BeforeEach
    void setUp() {
        commandFactory = new CommandFactory(messageOutputter);
    }

    @Test
    void identifiesQuitCommand() {
        QuitCommand command = (QuitCommand) commandFactory.newCommand("quit");

        assertTrue(command.isQuitCommand());
        assertThat(command).isInstanceOf(QuitCommand.class);
    }

    @Test
    void identifiesUnknownCommands() {
        UnknownCommand command = (UnknownCommand) commandFactory.newCommand("dont-know-this-command");

        assertFalse(command.isQuitCommand());
        assertThat(command).isInstanceOf(UnknownCommand.class);
    }

    @Test
    void identifiesPostMessageCommand() {
        PostCommand command = (PostCommand) commandFactory.newCommand("Alice -> I love the weather today");

        assertFalse(command.isQuitCommand());
        assertThat(command).isInstanceOf(PostCommand.class);
    }

}