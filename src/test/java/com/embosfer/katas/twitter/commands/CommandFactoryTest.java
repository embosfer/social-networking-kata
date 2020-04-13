package com.embosfer.katas.twitter.commands;

import com.embosfer.katas.twitter.domain.UserPostsCache;
import com.embosfer.katas.twitter.out.MessageOutputter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;

class CommandFactoryTest {

    CommandFactory commandFactory;
    MessageOutputter messageOutputter = mock(MessageOutputter.class);
    UserPostsCache userPostsCache = mock(UserPostsCache.class);

    @BeforeEach
    void setUp() {
        commandFactory = new CommandFactory(messageOutputter, userPostsCache);
    }

    @Test
    void identifiesUnknownCommands() {
        UnknownCommand command = (UnknownCommand) commandFactory.newCommand("dont know this command");

        assertFalse(command.isQuitCommand());
        assertThat(command).isInstanceOf(UnknownCommand.class);
        verifyNoInteractions(userPostsCache);
    }

}