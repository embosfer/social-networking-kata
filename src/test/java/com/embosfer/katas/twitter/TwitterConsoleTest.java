package com.embosfer.katas.twitter;

import com.embosfer.katas.twitter.commands.QuitCommand;
import com.embosfer.katas.twitter.commands.TwitterCommandInvoker;
import com.embosfer.katas.twitter.in.UserInput;
import com.embosfer.katas.twitter.out.MessageOutputter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TwitterConsoleTest {

    UserInput userInput = mock(UserInput.class);
    TwitterCommandInvoker commandProcessor = mock(TwitterCommandInvoker.class);
    MessageOutputter messageOutputter = mock(MessageOutputter.class);

    @BeforeEach
    void setUp() {
        when(commandProcessor.execute(any())).thenReturn(new QuitCommand(messageOutputter)); // make sure tests don't get stuck forever
    }

    @Test
    void commandsArePassedOverToCommandProcessor() {

        TwitterConsole twitter = new TwitterConsole(userInput, commandProcessor);

        when(userInput.nextInput()).thenReturn("a-user-command");

        twitter.start();

        verify(commandProcessor).execute("a-user-command");
    }

}