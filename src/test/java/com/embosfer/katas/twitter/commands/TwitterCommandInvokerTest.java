package com.embosfer.katas.twitter.commands;

import com.embosfer.katas.twitter.out.MessageOutputter;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class TwitterCommandInvokerTest {

    CommandFactory commandFactory = mock(CommandFactory.class);
    MessageOutputter messageOutputter = mock(MessageOutputter.class);
    TwitterCommandInvoker commandInvoker = new TwitterCommandInvoker(commandFactory);

    @Test
    void userInputGetsTransformedIntoACommandAndPrintedToConsole() {

        Command aCommand = mock(Command.class);
        when(commandFactory.newCommand("a-command-from-user")).thenReturn(aCommand);

        commandInvoker.execute("a-command-from-user");

        verify(aCommand).execute();
    }

}