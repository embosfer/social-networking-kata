package com.embosfer.katas.twitter.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CommandFactoryTest {

    CommandFactory commandFactory;

    @BeforeEach
    void setUp() {
        commandFactory = new CommandFactory();
    }

    @Test
    void identifiesQuitCommand() {
        Command quitCommand = commandFactory.newCommand("quit");

        assertThat(quitCommand).isInstanceOf(QuitCommand.class);
        assertThat(quitCommand.asOutMessage()).isEqualTo("Bye!");
    }

    @Test
    void identifiesUnknownCommands() {
        UnknownCommand unknownCommand = (UnknownCommand) commandFactory.newCommand("dont-know-this-command");

        assertThat(unknownCommand.asOutMessage()).contains("dont-know-this-command");
    }

    @Test
    void identifiesPostMessageCommand() {
        PostCommand postCommand = (PostCommand) commandFactory.newCommand("Alice -> I love the weather today");

        assertThat(postCommand.asOutMessage()).isEqualTo("Alice -> I love the weather today");
    }

}