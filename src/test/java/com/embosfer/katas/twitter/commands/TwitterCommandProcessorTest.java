package com.embosfer.katas.twitter.commands;

import com.embosfer.katas.twitter.out.MessageOutputter;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class TwitterCommandProcessorTest {

    CommandFactory commandFactory = mock(CommandFactory.class);
    MessageOutputter messageOutputter = mock(MessageOutputter.class);
    TwitterCommandProcessor commandProcessor = new TwitterCommandProcessor(commandFactory, messageOutputter);

    @Test
    void userInputGetsTransformedIntoACommandAndPrintedToConsole() {

        Command aCommand = mock(Command.class);
        when(commandFactory.newCommand("a-command-from-user")).thenReturn(aCommand);

        commandProcessor.process("a-command-from-user");

        verify(messageOutputter).printOut(aCommand.asOutMessage());
    }

}